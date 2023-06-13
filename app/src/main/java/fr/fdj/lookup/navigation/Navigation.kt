package fr.fdj.lookup.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.fdj.lookup.presentation.home.HomeScreen
import fr.fdj.lookup.presentation.home.HomeViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Teams.route) {
        composable(Screen.Teams.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val leaguesState = viewModel.leaguesState.collectAsState()
            val teamsState = viewModel.teamsState.collectAsState()
            HomeScreen(
                leaguesState = leaguesState.value,
                teamsState = teamsState.value,
                fetchTeamsForLeague = viewModel::fetchTeamsForLeague
            )
        }
    }
}
