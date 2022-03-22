package alistar.sample.githubusers

import alistar.sample.githubusers.features.search.presentation.SearchScreen
import alistar.sample.githubusers.features.search.presentation.SearchScreenViewModel
import alistar.sample.githubusers.features.userdetail.presentation.UserDetailScreen
import alistar.sample.githubusers.features.userdetail.presentation.UserDetailScreenViewModel
import alistar.sample.githubusers.libraries.navigation.DestinationArgs
import alistar.sample.githubusers.libraries.navigation.Destinations
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Navigator(navHostController: NavHostController) {
    val actions = remember { Actions(navHostController) }
    NavHost(navController = navHostController, startDestination = Destinations.SEARCH) {
        composable(route = Destinations.SEARCH) {
            val viewModel: SearchScreenViewModel = hiltViewModel()
            SearchScreen(
                searchScreenViewModel = viewModel,
                navigateToUserDetail = actions.openUserDetailScreen
            )
        }
        composable(
            route = "${Destinations.USER_DETAIL}/{${DestinationArgs.USERNAME}}",
            arguments = listOf(navArgument(DestinationArgs.USERNAME) { type = NavType.StringType })
        ) {
            val viewModel: UserDetailScreenViewModel = hiltViewModel()
            val context = LocalContext.current
            UserDetailScreen(
                userDetailScreenViewModel = viewModel,
                openLink = {
                    try {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(it) }
                        )
                    } catch (e: ActivityNotFoundException) {
                        if (BuildConfig.DEBUG)
                            e.printStackTrace()
                    }
                },
                navigateBack = actions.navigateBack
            )
        }
    }
}

class Actions(private val navHostController: NavHostController) {

    val openUserDetailScreen: (username: String) -> Unit = {
        navHostController.navigate("${Destinations.USER_DETAIL}/$it")
    }

    val navigateBack: () -> Unit = { navHostController.popBackStack() }
}
