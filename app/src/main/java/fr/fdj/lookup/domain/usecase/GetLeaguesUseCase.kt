package fr.fdj.lookup.domain.usecase

import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource
import fr.fdj.lookup.domain.model.League
import fr.fdj.lookup.domain.repository.LeaguesRepository

class GetLeaguesUseCase(
    private val leaguesRepository: LeaguesRepository
) {

    suspend fun execute(): Resource<List<League>, Failure.NetworkError> {
        return leaguesRepository.getAllLeagues()
    }
}
