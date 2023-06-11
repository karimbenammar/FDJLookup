package fr.fdj.lookup.domain.model

data class Team (
    val id: String,
    val name: String,
    val sport: String,
    val league: String,
    val nameAlt: String,
    val badgeUrl: String
)
