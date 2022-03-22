package alistar.sample.githubusers.features.search.presentation

import alistar.sample.githubusers.features.search.R
import alistar.sample.githubusers.features.search.item.UserItem
import alistar.sample.githubusers.features.search.presentation.ui.SearchBar
import alistar.sample.githubusers.features.search.presentation.ui.UserPlaceHolder
import alistar.sample.githubusers.libraries.design.theme.GitHubUsersTheme
import alistar.sample.githubusers.libraries.design.theme.HintColor
import alistar.sample.githubusers.libraries.design.theme.PlaceHolderColor
import alistar.sample.githubusers.libraries.design.theme.Red
import alistar.sample.githubusers.libraries.design.ui.ErrorState
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import alistar.sample.githubusers.libraries.design.R as DesignResource

@Composable
fun SearchScreen(
    searchScreenViewModel: SearchScreenViewModel,
    navigateToUserDetail: (username: String) -> Unit
) {
    SearchScreenLoader(
        searchScreenViewModel = searchScreenViewModel,
        navigateToUserDetail = navigateToUserDetail
    )
}

@Composable
private fun SearchScreenLoader(
    searchScreenViewModel: SearchScreenViewModel,
    navigateToUserDetail: (username: String) -> Unit
) {
    val viewState by searchScreenViewModel.uiState.collectAsState()
    val actions = SearchScreenActions(
        onInputTextChanged = searchScreenViewModel::onInputTextChanged,
        focusSearchBar = searchScreenViewModel::updateSearchBarState,
        resetToInitialState = searchScreenViewModel::resetToInitialState,
        openUserDetail = navigateToUserDetail
    )
    SearchScreenScaffold(viewState = viewState, actions = actions)
}

@Composable
internal fun SearchScreenScaffold(viewState: SearchScreenViewState, actions: SearchScreenActions) {
    Scaffold {
        val lazyPagingItems = viewState.pagingData?.collectAsLazyPagingItems()
        MainContent(
            inputText = viewState.inputText,
            lazyPagingItems = lazyPagingItems,
            actions = actions,
            isSearchBarHasFocus = viewState.isSearchBarFocused
        )
        SnackbarHost(lazyPagingItems)
    }
    if (viewState.lastSearchedQuery.isNotEmpty()) {
        BackHandler {
            actions.resetToInitialState()
        }
    }
}

@Composable
private fun MainContent(
    inputText: String,
    lazyPagingItems: LazyPagingItems<UserItem>?,
    actions: SearchScreenActions,
    isSearchBarHasFocus: Boolean
) {
    val searchBarHeight = 54.dp
    val searchBarHeightWithPadding = searchBarHeight + 16.dp
    val refreshLoadState = lazyPagingItems?.loadState?.refresh

    if (lazyPagingItems == null) {
        InitialState(startSearch = { actions.focusSearchBar(true) })
    } else if (refreshLoadState is LoadState.Loading) {
        UserListLoadingState(modifier = Modifier.padding(top = searchBarHeightWithPadding))
    } else if (refreshLoadState is LoadState.Error) {
        ErrorState(onRetry = { lazyPagingItems.retry() })
    } else if (lazyPagingItems.loadState.append.endOfPaginationReached && lazyPagingItems.itemCount == 0) {
        NoUsersFoundState()
    } else {
        val lazyListState = rememberLazyListState()
        KeyboardManager(lazyListState)
        UsersList(
            lazyListState = lazyListState,
            contentPadding = PaddingValues(top = searchBarHeightWithPadding, bottom = 70.dp),
            items = lazyPagingItems,
            actions = actions
        )
    }

    SearchBar(
        modifier = Modifier
            .padding(16.dp)
            .height(searchBarHeight),
        inputText = inputText,
        onKeyboardStateChanged = { isOpen -> if (!isOpen) actions.focusSearchBar(false) },
        requestFocus = isSearchBarHasFocus,
        onTextChanged = actions.onInputTextChanged
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun KeyboardManager(lazyListState: LazyListState) {
    val keyboardController = LocalSoftwareKeyboardController.current
    if (lazyListState.isScrollInProgress) {
        LaunchedEffect(lazyListState.isScrollInProgress) {
            keyboardController?.hide()
        }
    }
}

@Composable
private fun SnackbarHost(lazyPagingItems: LazyPagingItems<UserItem>?) {
    val state = remember { SnackbarHostState() }
    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            hostState = state,
            snackbar = { snackbarData ->
                Snackbar(
                    modifier = Modifier
                        .padding(16.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.error,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    action = {
                        TextButton(
                            onClick = {
                                lazyPagingItems?.retry()
                                state.currentSnackbarData?.dismiss()
                            }
                        ) {
                            Text(
                                text = snackbarData.actionLabel ?: "",
                                color = Red
                            )
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onError,
                ) {
                    Text(text = snackbarData.message)
                }
            }
        )
    }
    val pagingAppendLoadState = lazyPagingItems?.loadState?.append
    if (pagingAppendLoadState is LoadState.Error) {
        val errorText = stringResource(R.string.snackbarErrorText)
        val retryText = stringResource(R.string.retry)
        LaunchedEffect(pagingAppendLoadState.error) {
            state.showSnackbar(
                message = errorText,
                actionLabel = retryText,
                duration = SnackbarDuration.Indefinite
            )
        }
    }
}

@Composable
private fun NoUsersFoundState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = DesignResource.drawable.ic_github),
            contentDescription = "noUsersFoundPlaceHolderImage",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.surface)
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.noUsersFound),
            fontSize = 12.sp,
            color = HintColor
        )
    }
}

@Composable
private fun InitialState(startSearch: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = DesignResource.drawable.ic_github),
            contentDescription = "initialStatePlaceHolderImage",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.surface)
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.startSearch), color = HintColor)
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Rounded.ArrowForward,
                tint = HintColor,
                contentDescription = "arrowIcon"
            )
            FloatingActionButton(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .testTag("searchButton"),
                shape = RoundedCornerShape(10.dp),
                contentColor = MaterialTheme.colors.onBackground,
                onClick = { startSearch() }
            ) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "searchIcon")
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun InitialStatePreview() {
    GitHubUsersTheme {
        Surface {
            InitialState()
        }
    }
}

@Composable
private fun UsersList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    lazyListState: LazyListState = rememberLazyListState(),
    items: LazyPagingItems<UserItem>,
    actions: SearchScreenActions
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.testTag("usersList"),
            state = lazyListState,
            contentPadding = contentPadding
        ) {
            items(items) { item ->
                if (item == null) {
                    UserPlaceHolder()
                } else {
                    Row(
                        modifier = Modifier
                            .clickable { actions.openUserDetail(item.username) }
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = CircleShape)
                                .background(color = PlaceHolderColor),
                            painter = rememberImagePainter(data = item.photoUrl),
                            contentScale = ContentScale.Crop,
                            contentDescription = "userPhoto"
                        )
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            text = item.username,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UserListLoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("usersListPlaceHolder")
    ) {
        repeat(times = 20) {
            UserPlaceHolder()
        }
    }
}

@Preview(widthDp = 300, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun UserListLoadingPreview() {
    GitHubUsersTheme {
        Surface {
            UserListLoadingState()
        }
    }
}
