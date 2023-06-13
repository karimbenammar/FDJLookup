package fr.fdj.lookup.presentation.teams

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.fdj.lookup.domain.model.Team
import fr.fdj.lookup.presentation.components.ProgressIndicator
import fr.fdj.lookup.presentation.home.HomeViewModel.TeamsState

@Composable
fun TeamsList(
    state: TeamsState,
    teams: List<Team>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(teams.size) {
            TeamItem(team = teams[it])
        }
        if (state.isLoading) {
            item {
                ProgressIndicator()
            }
        }
    }
}