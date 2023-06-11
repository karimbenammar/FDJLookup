package fr.fdj.lookup.domain.repository

import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource
import fr.fdj.lookup.domain.model.League

interface LeaguesRepository {
    suspend fun getAllLeagues(): Resource<List<League>, Failure.NetworkError>
}
