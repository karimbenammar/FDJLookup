package fr.fdj.lookup.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.fdj.lookup.domain.model.League
import fr.fdj.lookup.domain.model.Team
import fr.fdj.lookup.presentation.components.ErrorMessage
import fr.fdj.lookup.presentation.components.ProgressIndicator
import fr.fdj.lookup.presentation.components.SearchBar
import fr.fdj.lookup.presentation.teams.TeamsList

@Composable
fun HomeScreen(
    teamsState: HomeViewModel.TeamsState,
    leaguesState: HomeViewModel.LeaguesState,
    fetchTeamsForLeague: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        SearchBar(
            suggestions = leaguesState.leagues?.map(League::name) ?: emptyList(),
            onSelect = { leagueName ->
                // Fetch the teams for the selected league
                fetchTeamsForLeague(leagueName)
            }
        )

        if (teamsState.isLoading || leaguesState.isLoading) {
            // Show a progress indicator
            ProgressIndicator()
        }

        Spacer(modifier = Modifier.weight(1f))

        teamsState.error?.let {
            ErrorMessage(message = it)
        }
        leaguesState.error?.let {
            ErrorMessage(message = it)
        }

        teamsState.teams?.let {
            if (it.isNotEmpty()) {
                // Show the list of teams
                TeamsList(state = teamsState, teams = it)
            } else {
                // Show a message when the list is empty
                ErrorMessage(message = "No teams found")
            }
        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenLoadingPreview() {
    HomeScreen(
        teamsState = HomeViewModel.TeamsState(isLoading = true),
        leaguesState = HomeViewModel.LeaguesState(isLoading = true),
        fetchTeamsForLeague = {}
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenErrorPreview() {
    HomeScreen(
        teamsState = HomeViewModel.TeamsState(error = "Error"),
        leaguesState = HomeViewModel.LeaguesState(error = "Error"),
        fetchTeamsForLeague = {}
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenLoadedPreview() {
    HomeScreen(
        teamsState = HomeViewModel.TeamsState(
            isLoading = false,
            teams = emptyList(),
            error = null
        ),
        leaguesState = HomeViewModel.LeaguesState(
            isLoading = false,
            leagues = listOf(
                League(
                    id = "1",
                    name = "League 1",
                    sport = "",
                    nameAlt = "",
                ),
                League(
                    id = "2",
                    name = "League 2",
                    sport = "",
                    nameAlt = "",
                ),
                League(
                    id = "3",
                    name = "League 3",
                    sport = "",
                    nameAlt = "",
                ),
            ),
            error = null
        ),
        fetchTeamsForLeague = {}
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenDataPreview() {
    HomeScreen(
        teamsState = HomeViewModel.TeamsState(
            isLoading = false,
            teams = listOf(
                Team(
                    id = "1",
                    name = "Team 1",
                    sport = "",
                    league = "",
                    nameAlt = "",
                    badgeUrl = "",
                ),
                Team(
                    id = "2",
                    name = "Team 2",
                    sport = "",
                    league = "",
                    nameAlt = "",
                    badgeUrl = "",
                ),
                Team(
                    id = "3",
                    name = "Team 3",
                    sport = "",
                    league = "",
                    nameAlt = "",
                    badgeUrl = "",
                ),
            ),
            error = null
        ),
        leaguesState = HomeViewModel.LeaguesState(
            isLoading = false,
            leagues = emptyList(),
            error = null
        ),
        fetchTeamsForLeague = {}
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenEmptyDataPreview() {
    HomeScreen(
        teamsState = HomeViewModel.TeamsState(
            isLoading = false,
            teams = emptyList(),
            error = null
        ),
        leaguesState = HomeViewModel.LeaguesState(
            isLoading = false,
            leagues = emptyList(),
            error = null
        ),
        fetchTeamsForLeague = {}
    )
}