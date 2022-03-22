package alistar.sample.githubusers.features.search.presentation.ui

import alistar.sample.githubusers.features.search.R
import alistar.sample.githubusers.libraries.design.theme.GitHubUsersTheme
import alistar.sample.githubusers.libraries.design.theme.HintColor
import alistar.sample.githubusers.libraries.design.utils.isKeyboardOpen
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import alistar.sample.githubusers.libraries.design.R as DesignResources

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onTextChanged: (text: String) -> Unit = {},
    onKeyboardStateChanged: (isOpen: Boolean) -> Unit = {},
    inputText: String = "",
    requestFocus: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(percent = 50)
            )
            .testTag("searchBar"),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val focusManager = LocalFocusManager.current
        val focusRequester = remember { FocusRequester() }
        val isKeyboardOpen by isKeyboardOpen()

        onKeyboardStateChanged(isKeyboardOpen)

        if (isKeyboardOpen.not()) {
            LaunchedEffect(isKeyboardOpen) {
                focusManager.clearFocus()
            }
        }

        if (requestFocus) {
            LaunchedEffect(requestFocus) {
                focusRequester.requestFocus()
            }
        }

        TextFiled(
            focusRequester = focusRequester,
            inputText = inputText,
            onTextChanged = onTextChanged
        )
        GitHubIcon(
            isInSearchState = isKeyboardOpen || requestFocus,
            onTextChanged = onTextChanged,
            focusManager = focusManager
        )
    }
}

@Composable
private fun RowScope.TextFiled(
    focusRequester: FocusRequester,
    inputText: String,
    onTextChanged: (text: String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(1f)
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused }
            .testTag("searchTextField"),
        value = inputText,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Rounded.Search,
                contentDescription = "searchIcon",
                tint = HintColor
            )
        },
        placeholder = {
            AnimatedVisibility(visible = isFocused.not()) {
                Text(text = stringResource(R.string.searchBarHint), color = HintColor)
            }
        },
        onValueChange = {
            onTextChanged(it)
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun RowScope.GitHubIcon(
    isInSearchState: Boolean,
    onTextChanged: (text: String) -> Unit,
    focusManager: FocusManager
) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .size(28.dp)
    ) {
        this@GitHubIcon.AnimatedVisibility(
            visible = isInSearchState,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            IconButton(onClick = {
                onTextChanged("")
                focusManager.clearFocus()
            }) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = "clearIcon")
            }
        }
        this@GitHubIcon.AnimatedVisibility(
            visible = isInSearchState.not(),
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = DesignResources.drawable.ic_github),
                tint = MaterialTheme.colors.onSurface,
                contentDescription = "gitHubIcon"
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    GitHubUsersTheme {
        Surface {
            SearchBar()
        }
    }
}
