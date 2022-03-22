package alistar.sample.githubusers.features.userdetail.presentation.ui

import alistar.sample.githubusers.libraries.design.R
import alistar.sample.githubusers.libraries.design.theme.GitHubUsersTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(onBackClicked: () -> Unit = {}) {
    TopAppBar(elevation = 0.dp, backgroundColor = MaterialTheme.colors.surface) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(28.dp),
                painter = painterResource(id = R.drawable.ic_github),
                tint = MaterialTheme.colors.onSurface,
                contentDescription = "gitHubIcon"
            )
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = "backButton"
                )
            }
        }
    }
}

@Preview(widthDp = 300)
@Composable
fun TopBarPreview() {
    GitHubUsersTheme {
        Surface {
            TopBar()
        }
    }
}
