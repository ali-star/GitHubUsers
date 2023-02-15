package alistar.sample.githubusers

import alistar.sample.githubusers.features.search.getBrowserIntent
import alistar.sample.githubusers.libraries.design.theme.GitHubUsersTheme
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.airbnb.android.showkase.models.Showkase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubUsersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()
                    Navigator(navHostController = navHostController)
                }
            }
        }
        Handler().postDelayed(
            { startActivity(Showkase.getBrowserIntent(this@MainActivity)) },
            5000
        )
    }
}

@Composable
fun TestComposable() {
    Box(
        modifier = Modifier.background(Color.Blue).padding(16.dp)
    ) {
        Text(text = "Test")
    }
}

@Preview(name = "test", group = "test")
@Composable
fun TestComposablePreview() {
    TestComposable()
}
