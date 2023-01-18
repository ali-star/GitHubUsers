@file:OptIn(ExperimentalLayoutApi::class)

package alistar.sample.githubusers.features.userdetail.presentation

import alistar.sample.githubusers.features.userdetail.R
import alistar.sample.githubusers.features.userdetail.item.UserDetailItem
import alistar.sample.githubusers.features.userdetail.presentation.ui.TopBar
import alistar.sample.githubusers.libraries.core.extensions.toQuantityString
import alistar.sample.githubusers.libraries.design.theme.GitHubUsersTheme
import alistar.sample.githubusers.libraries.design.theme.HintColor
import alistar.sample.githubusers.libraries.design.theme.PlaceHolderColor
import alistar.sample.githubusers.libraries.design.ui.ErrorState
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import alistar.sample.githubusers.libraries.design.R as DesignResource

@Composable
fun UserDetailScreen(
    userDetailScreenViewModel: UserDetailScreenViewModel,
    openLink: (link: String) -> Unit = {},
    navigateBack: () -> Unit
) {
    UserDetailScreenLoader(
        userDetailScreenViewModel = userDetailScreenViewModel,
        openLink = openLink,
        navigateBack = navigateBack
    )
}

@Composable
private fun UserDetailScreenLoader(
    userDetailScreenViewModel: UserDetailScreenViewModel,
    openLink: (link: String) -> Unit,
    navigateBack: () -> Unit
) {
    val state by userDetailScreenViewModel.uiState.collectAsState()
    val actions = UserDetailScreenActions(
        navigateBack = navigateBack,
        retry = userDetailScreenViewModel::retry,
        openLink = openLink
    )

    UserDetailScaffold(viewState = state, actions = actions)
}

@Composable
internal fun UserDetailScaffold(
    viewState: UserDetailScreenViewState,
    actions: UserDetailScreenActions
) {
    Scaffold(
        topBar = { TopBar(onBackClicked = actions.navigateBack) },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
                    .padding(vertical = 36.dp)
            ) {
                when (viewState) {
                    is UserDetailScreenViewState.Error -> ErrorState(
                        modifier = Modifier.align(Alignment.Center),
                        onRetry = actions.retry
                    )
                    is UserDetailScreenViewState.Loading -> LoadingState()
                    is UserDetailScreenViewState.Result -> ResultState(
                        userDetail = viewState.userDetailItem,
                        actions = actions
                    )
                }
            }
        }
    )
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .background(color = PlaceHolderColor)
        )
        Spacer(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(width = 100.dp, height = 24.dp)
                .background(
                    color = PlaceHolderColor,
                    shape = RoundedCornerShape(percent = 50)
                )
        )
        Spacer(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(width = 150.dp, height = 24.dp)
                .background(
                    color = PlaceHolderColor,
                    shape = RoundedCornerShape(percent = 50)
                )
        )
        Spacer(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(width = 200.dp, height = 24.dp)
                .background(
                    color = PlaceHolderColor,
                    shape = RoundedCornerShape(percent = 50)
                )
        )
        Spacer(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(208.dp)
                .background(
                    color = PlaceHolderColor,
                    shape = RoundedCornerShape(15.dp)
                )
        )
    }
}

@Preview
@Composable
fun LoadingStatePreview() {
    GitHubUsersTheme {
        Surface {
            LoadingState()
        }
    }
}

@Composable
private fun ResultState(
    userDetail: UserDetailItem,
    actions: UserDetailScreenActions = UserDetailScreenActions()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colors.onSecondary),
            painter = rememberAsyncImagePainter(model = userDetail.photoUrl),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = userDetail.username,
            fontSize = 23.sp,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
        )
        if (userDetail.name != null) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                fontSize = 20.sp,
                text = userDetail.name,
                color = HintColor
            )
        }
        UserNetworkInfo(userDetail)
        UserInfo(userDetail, actions)
    }
}

@Composable
private fun UserNetworkInfo(userDetail: UserDetailItem) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Group,
            contentDescription = "userNetworkIcon",
            tint = HintColor
        )
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground)) {
                append(userDetail.followersCount.toQuantityString())
            }
            withStyle(style = SpanStyle(color = HintColor)) {
                if (userDetail.followingCount > 1) {
                    append(" ${stringResource(R.string.followers)} • ")
                } else {
                    append(" ${stringResource(R.string.follower)} • ")
                }
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground)) {
                append(userDetail.followingCount.toQuantityString())
            }
            withStyle(style = SpanStyle(color = HintColor)) {
                append(" ${stringResource(id = R.string.following)}")
            }
        }
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = text,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun UserInfo(userDetail: UserDetailItem, actions: UserDetailScreenActions) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
            .border(
                width = 1.dp,
                color = PlaceHolderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp)),
    ) {
        val items = userInfoItems(userDetail, actions)
        items.forEachIndexed { index, triple ->
            if (index > 0) {
                Divider(color = PlaceHolderColor)
            }
            UserInfoItem(image = triple.first, text = triple.second, onClick = triple.third)
        }
    }
}

@Composable
private fun userInfoItems(
    userDetail: UserDetailItem,
    actions: UserDetailScreenActions
): List<Triple<@Composable () -> Unit, String, () -> Unit>> = buildList {
    userDetail.company?.let {
        add(
            Triple(
                first = {
                    Icon(
                        imageVector = Icons.Outlined.Business,
                        contentDescription = "company",
                        tint = MaterialTheme.colors.onBackground
                    )
                },
                second = it,
                third = { }
            )
        )
    }
    userDetail.location?.let {
        add(
            Triple(
                first = {
                    Icon(
                        imageVector = Icons.Outlined.Place,
                        contentDescription = "location",
                        tint = MaterialTheme.colors.onBackground
                    )
                },
                second = it,
                third = { }
            )
        )
    }
    userDetail.twitter?.let {
        add(
            Triple(
                first = {
                    Icon(
                        painter = painterResource(
                            id = DesignResource.drawable.ic_twitter
                        ),
                        contentDescription = "twitterUsername",
                        tint = MaterialTheme.colors.onBackground
                    )
                },
                second = it,
                third = { actions.openLink("https://twitter.com/$it") }
            )
        )
    }
    userDetail.blog?.let {
        add(
            Triple(
                first = {
                    Icon(
                        imageVector = Icons.Outlined.Link,
                        contentDescription = "blog",
                        tint = MaterialTheme.colors.onBackground
                    )
                },
                second = it,
                third = {
                    actions.openLink(
                        it.let { blog ->
                            if (blog.startsWith("http")) blog else "https://$blog"
                        }
                    )
                }
            )
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ResultStatePreview() {
    GitHubUsersTheme {
        Surface {
            val userDetail = UserDetailItem(
                username = "ali-star",
                name = "Ali Mohseni Rad",
                followingCount = 7,
                followersCount = 1000,
                photoUrl = "",
                company = "Company",
                location = "Amsterdam, Netherlands",
                twitter = "ali-star",
                blog = "alimohsenirad.ir"
            )
            ResultState(userDetail = userDetail)
        }
    }
}

@Composable
fun UserInfoItem(
    image: @Composable () -> Unit,
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        image()
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
