package com.example.softwaredesign.week5.practice3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.R

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var extraEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var imageView: ImageView
    private var selectedImage: Bitmap? = null

    companion object {
        const val REQUEST_CODE = 1
        const val GALLERY_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week5_ex3_activity_main)

        nameEditText = findViewById(R.id.editName)
        ageEditText = findViewById(R.id.editAge)
        phoneEditText = findViewById(R.id.editPhone)
        addressEditText = findViewById(R.id.editAddress)
        extraEditText = findViewById(R.id.editExtra)
        saveButton = findViewById(R.id.buttonSave)
        imageView = findViewById(R.id.imageView)

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_CODE)
        }

        saveButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java).apply {
                putExtra("name", nameEditText.text.toString())
                putExtra("age", ageEditText.text.toString())
                putExtra("phone", phoneEditText.text.toString())
                putExtra("address", addressEditText.text.toString())
                putExtra("extra", extraEditText.text.toString())
                selectedImage?.let {
                    val resized = Bitmap.createScaledBitmap(it, 300, 300, false)
                    putExtra("image", resized)
                }
            }
            startActivityForResult(intent, REQUEST_CODE)
        }

        // 수정 모드로 돌아온 경우 기존 데이터 복원
        intent.getStringExtra("name")?.let { nameEditText.setText(it) }
        intent.getStringExtra("age")?.let { ageEditText.setText(it) }
        intent.getStringExtra("phone")?.let { phoneEditText.setText(it) }
        intent.getStringExtra("address")?.let { addressEditText.setText(it) }
        intent.getStringExtra("extra")?.let { extraEditText.setText(it) }
        intent.getParcelableExtra<Bitmap>("image")?.let {
            selectedImage = it
            imageView.setImageBitmap(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_CODE -> {
                    val uri: Uri? = data?.data
                    uri?.let {
                        selectedImage = if (Build.VERSION.SDK_INT >= 29) {
                            val source = ImageDecoder.createSource(contentResolver, uri)
                            ImageDecoder.decodeBitmap(source)
                        } else {
                            MediaStore.Images.Media.getBitmap(contentResolver, uri)
                        }
                        imageView.setImageBitmap(selectedImage)
                    }
                }
                REQUEST_CODE -> {
                    nameEditText.setText("")
                    ageEditText.setText("")
                    phoneEditText.setText("")
                    addressEditText.setText("")
                    extraEditText.setText("")
                    imageView.setImageResource(android.R.drawable.ic_menu_gallery)

                }
            }
        }
    }
}