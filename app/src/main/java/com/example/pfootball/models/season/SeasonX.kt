package com.example.pfootball.models.season

data class SeasonX(
    val coverage: Coverage,
    val current: Boolean,
    val end: String,
    val start: String,
    val year: Int
)