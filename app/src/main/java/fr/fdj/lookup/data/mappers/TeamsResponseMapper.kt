package fr.fdj.lookup.data.mappers

import fr.fdj.lookup.data.remote.teams.TeamsResponse
import fr.fdj.lookup.data.remote.teams.TeamsResponse.ApiTeam
import fr.fdj.lookup.domain.model.Team

fun ApiTeam.toTeam() = Team(
    id = id,
    name = name,
    sport = sport,
    league = league,
    nameAlt = nameAlt,
    badgeUrl = badgeUrl
)

fun TeamsResponse.toTeamsList() = teams?.map { it.toTeam() }
