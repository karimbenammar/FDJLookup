package fr.fdj.lookup.navigation

sealed class Screen(val route: String) {
    object Teams : Screen("teams_screen")
}
