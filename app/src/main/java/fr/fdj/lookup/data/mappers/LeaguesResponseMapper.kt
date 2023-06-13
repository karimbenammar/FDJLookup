package fr.fdj.lookup.data.mappers

import fr.fdj.lookup.data.remote.leagues.LeaguesResponse
import fr.fdj.lookup.data.remote.leagues.LeaguesResponse.ApiLeague
import fr.fdj.lookup.domain.model.League

fun ApiLeague.toLeague() = League(
    id = id,
    name = name,
    sport = sport,
    nameAlt = nameAlt
)

fun LeaguesResponse.toLeaguesList() = leagues.map { it.toLeague() }
