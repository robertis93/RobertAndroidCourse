package com.example.robertandroidcourse2021

import java.io.Serializable

data class ContactModel(val imageResId: Int,
                        val name: String,
                        val numberOne: String,
                        val numberTwo: String,
                        val emailFirst: String,
                        val emailSecond: String,
                        val description: String,
                        var text: String = "") : Serializable


