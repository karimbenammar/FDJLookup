package fr.fdj.lookup.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import fr.fdj.lookup.presentation.teams.TeamsScreen
import fr.fdj.lookup.presentation.teams.TeamsViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Teams.route) {
        composable(Screen.Teams.route) {
            //val viewModel = hiltViewModel<TeamsViewModel>()
            //val state = viewModel.state.collectAsState()
            //TeamsScreen(navController = navController, state = state.value)
        }
    }
}
