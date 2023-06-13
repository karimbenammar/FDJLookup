package fr.fdj.lookup.presentation.teams

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        columns = GridCells.Fixed(3),
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