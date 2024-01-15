package com.example.pfootball.models.season

data class Response(
    val country: Country,
    val league: League,
    val seasons: List<SeasonX>
)