package com.example.softwaredesign.week7.practice1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.databinding.Week7Ex1ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: Week7Ex1ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Week7Ex1ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Write 버튼 클릭 시
        binding.buttonWrite.setOnClickListener {
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val ageText = binding.editAge.text.toString()
            val age = ageText.toIntOrNull() ?: -1

            if (name.isBlank() || email.isBlank() || age == -1) {
                Toast.makeText(this, "모든 값을 올바르게 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = hashMapOf(
                "name" to name,
                "email" to email,
                "age" to age
            )

            db.collection("user")
                .add(user)
                .addOnSuccessListener { documentRef ->
                    val prevText = binding.textResult.text.toString()
                    val newText = "Success : ${documentRef.id}\n"
                    binding.textResult.text = "$prevText$newText"
                }
                .addOnFailureListener {
                    Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show()
                }
        }

        // Read 버튼 클릭 시
        binding.buttonRead.setOnClickListener {
            db.collection("user")
                .get()
                .addOnSuccessListener { result ->
                    val stringBuilder = StringBuilder()
                    for (doc in result) {
                        val name = doc["name"]
                        val email = doc["email"]
                        val age = doc["age"]
                        stringBuilder.append("(name=$name, email=$email, age=$age)\n")
                    }
                    binding.textResult.text = stringBuilder.toString()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "읽기 실패", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
