package alistar.sample.githubusers.features.userdetail.presentation

data class UserDetailScreenActions(
    val navigateBack: () -> Unit = {},
    val retry: () -> Unit = {},
    val openLink: (link: String) -> Unit = {}
)
