package com.example.pfootball.models

data class Player(
    val errors: List<Any>,
    val `get`: String,
    val paging: PagingX,
    val parameters: ParametersX,
    val response: List<ResponseX>,
    val results: Int
)