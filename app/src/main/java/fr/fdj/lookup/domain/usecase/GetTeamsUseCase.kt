package fr.fdj.lookup.domain.usecase

import fr.fdj.lookup.domain.model.Team
import fr.fdj.lookup.domain.repository.TeamsRepository
import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource
import fr.fdj.lookup.domain.util.coFold

class GetTeamsUseCase(
    private val teamsRepository: TeamsRepository
) {

    suspend fun execute(
        leagueName: String
    ): Resource<List<Team>, Failure.NetworkError> {
        return teamsRepository.getTeamsForLeague(leagueName).coFold({ teams ->
            return@coFold Resource.Success(filterTeams(sortTeams(teams)))
        }, { failure ->
            return@coFold Resource.Error(failure)
        })
    }

    // This method sorts a given list of teams anti-alphabetically
    private fun sortTeams(teams: List<Team>): List<Team> {
        return teams.sortedByDescending { it.name }
    }

    // This method filters a given list of teams by removing one every two teams
    private fun filterTeams(teams: List<Team>): List<Team> {
        return teams.filterIndexed { index, _ -> index % 2 == 0 }
    }
}
