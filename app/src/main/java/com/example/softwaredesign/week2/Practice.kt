package com.example.softwaredesign.week2

fun main() {
    var a = readLine()
    println("입력 받을 숫자:  ${a!!}")

    for (i in 1..a.toInt() step 1) {
        if (i % 2 != 0) {
            print("${i!!} ")
        }
    }
    println()
}