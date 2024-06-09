package alistar.sample.githubusers.features.search.presentation

data class SearchScreenActions(
    val onInputTextChanged: (query: String) -> Unit = {},
    val focusSearchBar: (focus: Boolean) -> Unit = {},
    val resetToInitialState: () -> Unit = {},
    val openUserDetail: (username: String) -> Unit = {},
    val getUsers: (query: String) -> Unit = {},
)
