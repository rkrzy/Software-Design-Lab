package com.example.softwaredesign.week6.practice1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.databinding.Week6Ex1ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var downloadId: Long = -1L
    private lateinit var downloadManager: DownloadManager
    private var currentDialog: AlertDialog? = null  // ✅ 다이얼로그 객체 저장

    // 브로드캐스트 리시버 선언
    private val onDownloadComplete = object : BroadcastReceiver() {
        @SuppressLint("Range")
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            Log.d("DownloadDebug", "onReceive called. action=${intent.action}, receivedId=$id, downloadId=$downloadId")
            // 무조건 다이얼로그 닫기!
            currentDialog?.dismiss()

            val query = DownloadManager.Query().setFilterById(id)
            val cursor = downloadManager.query(query)
            if (!cursor.moveToFirst()) {
                Log.d("DownloadDebug", "Cursor moveToFirst() failed")
                return
            }
            val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
            val status = cursor.getInt(columnIndex)
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
            } else if (status == DownloadManager.STATUS_FAILED) {
                Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "week6_ex1"
        super.onCreate(savedInstanceState)
        val binding = Week6Ex1ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        // todo 1
        val intentFilter = IntentFilter().apply {
            addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        }

        //여기가 문제의 구간이였다 onReceive가 호출이 안되서 안에 있는 코드가 실행이 안되었다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d("MyReceiver", "Registering receiver with RECEIVER_NOT_EXPORTED using Activity context")
            registerReceiver(onDownloadComplete, intentFilter, Context.RECEIVER_EXPORTED)
        } else {
            Log.d("MyReceiver", "Registering receiver using Activity context")
            registerReceiver(onDownloadComplete, intentFilter)
        }
        // 다운로드 버튼
        binding.downloadBtn.setOnClickListener {
            val downloadUrl = "https://cse.pusan.ac.kr/sites/cse/download/201912_cse_newsletter_vol_29.pdf"
            val fileName = "dev_submit_${System.currentTimeMillis()}.pdf"

            // ✅ 다운로드 중 다이얼로그 생성
            val progressDialog = AlertDialog.Builder(this)
                .setTitle("Downloading")
                .setMessage("Download currently...")
                .setCancelable(false)
                .create()

            progressDialog.show()
            currentDialog = progressDialog

            val request = DownloadManager.Request(Uri.parse(downloadUrl))
                .setTitle("Downloading file")
                .setDescription("Downloading newsletter")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

            downloadId = downloadManager.enqueue(request)
        }

        // 취소 버튼
        binding.cancelBtn.setOnClickListener {
            if (downloadId != -1L) {
                downloadManager.remove(downloadId)
                Toast.makeText(this, "Download canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onDownloadComplete)
    }
}
