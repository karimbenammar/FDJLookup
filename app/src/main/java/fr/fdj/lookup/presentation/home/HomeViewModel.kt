package fr.fdj.lookup.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.lookup.domain.model.League
import fr.fdj.lookup.domain.model.Team
import fr.fdj.lookup.domain.usecase.GetLeaguesUseCase
import fr.fdj.lookup.domain.usecase.GetTeamsUseCase
import fr.fdj.lookup.domain.util.coFold
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getLeaguesUseCase: GetLeaguesUseCase,
    private val getTeamsUseCase: GetTeamsUseCase
) : ViewModel() {

    private val _leaguesState = MutableStateFlow(LeaguesState())
    val leaguesState = _leaguesState.asStateFlow()

    private val _teamsState = MutableStateFlow(TeamsState())
    val teamsState = _teamsState.asStateFlow()

    init {
        fetchLeagues()
    }

    private fun fetchLeagues() {
        viewModelScope.launch {
            _leaguesState.update { it.copy(isLoading = true) }

            getLeaguesUseCase.execute().coFold({ leagues ->
                _leaguesState.update {
                    it.copy(
                        leagues = leagues,
                        error = null
                    )
                }
            }, { failure ->
                _leaguesState.update { it.copy(leagues = null, error = failure.message) }
            })

            _leaguesState.update { it.copy(isLoading = false) }
        }
    }

    fun fetchTeamsForLeague(leagueName: String) {
        viewModelScope.launch {
            _teamsState.update { it.copy(isLoading = true) }

            getTeamsUseCase.execute(leagueName).coFold({ teams ->
                _teamsState.update {
                    it.copy(
                        teams = teams,
                        error = null
                    )
                }
            }, { failure ->
                _teamsState.update { it.copy(teams = null, error = failure.message) }
            })

            _teamsState.update { it.copy(isLoading = false) }
        }
    }

    data class TeamsState(
        val isLoading: Boolean = false,
        val teams: List<Team>? = null,
        val error: String? = null
    )

    data class LeaguesState(
        val isLoading: Boolean = false,
        val leagues: List<League>? = null,
        val error: String? = null
    )
}