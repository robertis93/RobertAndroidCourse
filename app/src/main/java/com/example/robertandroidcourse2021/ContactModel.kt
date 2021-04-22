package com.example.robertandroidcourse2021

import java.time.Month

data class ContactModel(
    val id: Int,
    val imageResId: Int,
    val name: String,
    val dayOfBirth : Int,
    val monthOfBirth: Int,
    val numberOne: String,
    val numberTwo: String,
    val emailFirst: String,
    val emailSecond: String,
    val description: String
)


