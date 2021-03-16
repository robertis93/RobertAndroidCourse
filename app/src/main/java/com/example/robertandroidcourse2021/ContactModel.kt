package com.example.robertandroidcourse2021

import java.io.Serializable

data class ContactModel(val id: Int,
                        val imageResId: Int,
                        val name: String,
                        val numberOne: String,
                        val numberTwo: String,
                        val emailFirst: String,
                        val emailSecond: String,
                        val description: String
)


