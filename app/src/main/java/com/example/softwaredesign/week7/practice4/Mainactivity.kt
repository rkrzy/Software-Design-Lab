package com.example.softwaredesign.week7.practice4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.databinding.Week7Ex4ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: Week7Ex4ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()
    private var oSysMainLoop = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Week7Ex4ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firestore에서 값 읽어오기 (실시간 업데이트)
        val adocRef = db.collection("user").document("test@gmail.com")
        adocRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                binding.textView.text = snapshot.data!!["xloc"].toString()
            }
        }

        // 랜덤 값 생성 및 저장
        if (oSysMainLoop == 0) {
            oSysMainLoop = 1
            timer(period = 1500, initialDelay = 1000) {
                if (oSysMainLoop != 1) cancel()

                val axLoc = hashMapOf("xloc" to (0..100).random())
                db.collection("user").document("test@gmail.com")
                    .set(axLoc)
                    .addOnSuccessListener {
                        runOnUiThread {
                            Toast.makeText(applicationContext, "저장 성공", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        runOnUiThread {
                            Toast.makeText(applicationContext, "저장 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
