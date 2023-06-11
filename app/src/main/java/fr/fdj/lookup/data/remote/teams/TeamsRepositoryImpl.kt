package fr.fdj.lookup.data.remote.teams

import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource
import fr.fdj.lookup.data.mappers.toTeamsList
import fr.fdj.lookup.domain.model.Team
import fr.fdj.lookup.domain.repository.TeamsRepository

class TeamsRepositoryImpl(private val api: TeamsApi) : TeamsRepository {

    override suspend fun getTeamsForLeague(league: String): Resource<List<Team>, Failure.NetworkError> {
        return try {
            Resource.Success(api.getTeamsForLeague(league).toTeamsList())
        } catch (e: Exception) {
            Resource.Error(Failure.NetworkError(e.message ?: "Unknown error"))
        }
    }
}