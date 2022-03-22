package alistar.sample.githubusers.libraries.design.ui

import alistar.sample.githubusers.libraries.design.R
import alistar.sample.githubusers.libraries.design.extensions.clickableWithNoRipple
import alistar.sample.githubusers.libraries.design.theme.DimPlaceHolderColor
import alistar.sample.githubusers.libraries.design.theme.GitHubUsersTheme
import alistar.sample.githubusers.libraries.design.theme.HintColor
import alistar.sample.githubusers.libraries.design.theme.Red
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorState(modifier: Modifier = Modifier, onRetry: () -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickableWithNoRipple { onRetry() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_github),
            contentDescription = "errorStatePlaceHolderImage",
            colorFilter = ColorFilter.tint(DimPlaceHolderColor)
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.error),
            fontSize = 12.sp,
            color = Red,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.errorText),
            fontSize = 12.sp,
            color = HintColor
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.tapToRetry),
            fontSize = 12.sp,
            color = HintColor
        )
    }
}

@Preview
@Composable
fun ErrorStatePreview() {
    GitHubUsersTheme {
        Surface {
            ErrorState()
        }
    }
}
