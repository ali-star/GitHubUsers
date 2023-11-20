package alistar.sample.githubusers.presentation.feature.search

data class SearchScreenActions(
    val onInputTextChanged: (query: String) -> Unit = {},
    val focusSearchBar: (focus: Boolean) -> Unit = {},
    val resetToInitialState: () -> Unit = {},
    val openUserDetail: (username: String) -> Unit = {}
)
