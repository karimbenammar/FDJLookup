package fr.fdj.lookup.domain.repository

import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource
import fr.fdj.lookup.domain.model.Team

interface TeamsRepository {
    suspend fun getTeamsForLeague(league: String): Resource<List<Team>, Failure.NetworkError>
}
