package com.example.softwaredesign.week3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.R

class CalculatorActivity : ComponentActivity() {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week3_calculator)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        tvResult = findViewById(R.id.tvResult)

        // 각 버튼 클릭 이벤트 설정
        btnAdd.setOnClickListener { calculate("+") }
        btnSubtract.setOnClickListener { calculate("-") }
        btnMultiply.setOnClickListener { calculate("*") }
        btnDivide.setOnClickListener { calculate("/") }
    }

    private fun calculate(operator: String) {
        val num1Text = etNum1.text.toString()
        val num2Text = etNum2.text.toString()

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            tvResult.text = "계산 결과: 숫자를 입력하세요"
            return
        }

        val num1 = num1Text.toDoubleOrNull()
        val num2 = num2Text.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            tvResult.text = "계산 결과: 유효한 숫자를 입력하세요"
            return
        }

        val result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else {
                tvResult.text = "계산 결과: 0으로 나눌 수 없습니다"
                return
            }

            else -> 0.0
        }

        // 결과를 소수점 없이 변환
        val finalResult = if (result % 1.0 == 0.0) result.toInt() else result

        // 결과를 Num1에 업데이트하고 Num2 비우기
        etNum1.setText(finalResult.toString())
        etNum2.text.clear()

        // 결과 표시
        tvResult.text = "계산 결과: $finalResult"
    }
}