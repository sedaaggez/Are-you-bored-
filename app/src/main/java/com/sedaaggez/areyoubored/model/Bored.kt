package com.sedaaggez.areyoubored.model

data class Bored(
    val activity: String,
    val type: String,
    val participants: Double,
    val price: Double,
    val link: String,
    val key: String,
    val accessibility: Double,
)