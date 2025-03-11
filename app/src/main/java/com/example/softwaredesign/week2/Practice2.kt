package com.example.softwaredesign.week2

enum class Food{pizza, burger, chicken}

class Lunch(var menu:Food, var price: Int){
    fun choice_lunch(){
        println("menu : ${menu}, price : ${price}")
    }
}

fun main(){

    val lunch = mutableListOf<Lunch>()

    lunch.add(Lunch(Food.pizza, price = 15000))
    lunch.add(Lunch(Food.burger, price = 7000))
    lunch.add(Lunch(Food.chicken, price = 25000))

    var myLunch = lunch.filter { it.price < 10000 }
    for (lunch in myLunch) {
        lunch.choice_lunch()
    }
}