package com.example.pfootball.models.season

import com.example.pfootball.models.Paging

data class Season(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)