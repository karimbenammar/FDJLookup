package fr.fdj.lookup.domain.usecase

import fr.fdj.lookup.domain.model.Team
import fr.fdj.lookup.domain.repository.TeamsRepository
import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource

class GetTeamsUseCase(
    private val teamsRepository: TeamsRepository
) {

    suspend fun execute(
        league: String
    ): Resource<List<Team>, Failure.NetworkError> {
        return teamsRepository.getTeamsForLeague(league)
    }
}
