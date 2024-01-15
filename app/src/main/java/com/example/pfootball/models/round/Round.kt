package com.example.pfootball.models.round

data class Round(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<String>,
    val results: Int
)