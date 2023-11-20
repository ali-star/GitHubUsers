package alistar.sample.githubusers.presentation.feature.userdetail

data class UserDetailScreenActions(
    val navigateBack: () -> Unit = {},
    val retry: () -> Unit = {},
    val openLink: (link: String) -> Unit = {}
)
