package alistar.sample.githubusers.presentation.feature.search

import alistar.sample.githubusers.presentation.api.search.model.UserItem
import alistar.sample.githubusers.presentation.api.search.usecase.SearchUsersUseCase
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import androidx.paging.PagingData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchScreenViewModelTest : TestCase() {

    private val robot = Robot()

    @Test
    fun test_onInputTextChanged() {
        RUN_UNIT_TEST(robot) {
            GIVEN { mockSearchUsersUseCase() }
            WHEN { createViewModel() }
            AND { search("octocat") }
            THEN { checkSearchUserIsCalled(times = 0, timeout = 0) }
            AND { checkSearchUserIsCalled(times = 1, timeout = 1000) }
        }
    }

    @Test
    fun test_continuous_onInputTextChanged() {
        RUN_UNIT_TEST(robot) {
            GIVEN { mockSearchUsersUseCase() }
            WHEN { createViewModel() }
            AND { search("octo") }
            AND { search("octocat") }
            AND { checkSearchUserIsCalled(times = 1, timeout = 1000) }
        }
    }

    private class Robot : BaseRobot() {

        private val searchUsersUseCase: SearchUsersUseCase = mockk()
        private lateinit var viewModel: SearchScreenViewModel

        fun mockSearchUsersUseCase() {
            every { searchUsersUseCase(any()) } answers {
                flow {
                    val users = buildList {
                        repeat(5) {
                            add(UserItem(username = "user $it", photoUrl = "photoUrl"))
                        }
                    }
                    emit(PagingData.from(users))
                }.flowOn(Dispatchers.IO)
            }
        }

        fun createViewModel() {
            viewModel = SearchScreenViewModel(searchUsersUseCase)
        }

        fun search(text: String) {
            viewModel.onInputTextChanged(text)
        }

        fun checkSearchUserIsCalled(times: Int = 1, timeout: Long = 1000) {
            verify(exactly = times, timeout = timeout) { searchUsersUseCase(any()) }
        }
    }
}
