package fr.fdj.lookup.data.remote.leagues

import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource
import fr.fdj.lookup.data.mappers.toLeaguesList
import fr.fdj.lookup.domain.model.League
import fr.fdj.lookup.domain.repository.LeaguesRepository

class LeaguesRepositoryImpl(private val api: LeaguesApi) : LeaguesRepository {

    override suspend fun getAllLeagues(): Resource<List<League>, Failure.NetworkError> {
        return try {
            Resource.Success(api.getLeagues().toLeaguesList())
        } catch (e: Exception) {
            Resource.Error(Failure.NetworkError(e.message ?: "Unknown error"))
        }
    }
}