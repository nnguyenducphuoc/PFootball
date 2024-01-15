package com.example.pfootball.models

data class Penalty(
    val commited: Any,
    val missed: Int,
    val saved: Any,
    val scored: Int,
    val won: Int
)