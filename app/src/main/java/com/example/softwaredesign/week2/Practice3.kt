package com.example.softwaredesign.week2

fun main() {

    while(true){
        val input = readLine()!!
        if (input.all { it.isLowerCase() }) {
            println(input.uppercase())
        } else {
            val errorPart = input.filter { !it.isLowerCase() }
            println("error with = $errorPart")
            break;
        }
    }
}
