package com.example.pfootball.models

data class Statistic(
    val cards: Cards,
    val dribbles: Dribbles,
    val duels: Duels,
    val fouls: Fouls,
    val games: Games,
    val goals: GoalsX,
    val league: LeagueX,
    val passes: Passes,
    val penalty: Penalty,
    val shots: Shots,
    val substitutes: Substitutes,
    val tackles: Tackles,
    val team: TeamX
)