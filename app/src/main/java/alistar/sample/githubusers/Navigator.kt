package alistar.sample.githubusers

import alistar.sample.githubusers.features.search.presentation.SearchScreen
import alistar.sample.githubusers.features.search.presentation.SearchScreenViewModel
import alistar.sample.githubusers.libraries.navigation.Destinations
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigator(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Destinations.SEARCH) {
        composable(route = Destinations.SEARCH) {
            val viewModel: SearchScreenViewModel = hiltViewModel()
            SearchScreen(searchScreenViewModel = viewModel, navigateToUserDetail = {})
        }
    }
}
