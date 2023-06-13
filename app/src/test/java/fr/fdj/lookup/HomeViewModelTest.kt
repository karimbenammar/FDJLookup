package fr.fdj.lookup

import fr.fdj.lookup.domain.repository.LeaguesRepository
import fr.fdj.lookup.domain.repository.TeamsRepository
import fr.fdj.lookup.domain.usecase.GetLeaguesUseCase
import fr.fdj.lookup.domain.usecase.GetTeamsUseCase
import fr.fdj.lookup.domain.util.Failure
import fr.fdj.lookup.domain.util.Resource
import fr.fdj.lookup.fixtures.LeaguesFixtures
import fr.fdj.lookup.fixtures.TeamsFixtures
import fr.fdj.lookup.presentation.home.HomeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var teamsRepository: TeamsRepository

    @Mock
    private lateinit var leaguesRepository: LeaguesRepository

    private lateinit var getTeamsUseCase: GetTeamsUseCase
    private lateinit var getLeaguesUseCase: GetLeaguesUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        getTeamsUseCase = GetTeamsUseCase(teamsRepository)
        getLeaguesUseCase = GetLeaguesUseCase(leaguesRepository)
    }

    @Test
    fun `Given a list of leagues, When fetching the list of leagues, Then leagues are successfully fetched`() = runTest {
        // Given
        `when`(getLeaguesUseCase.execute())
            .thenReturn(Resource.Success(LeaguesFixtures.leaguesResult))

        // When
        viewModel = HomeViewModel(getLeaguesUseCase, getTeamsUseCase)

        // Then
        assert(!viewModel.leaguesState.value.isLoading)
        assert(viewModel.leaguesState.value.leagues == LeaguesFixtures.leaguesResult)
    }

    @Test
    fun `Given a random error, When fetching the list of leagues, Then fetching fails`() = runTest {
        // Given
        `when`(getLeaguesUseCase.execute())
            .thenReturn(Resource.Error(Failure.NetworkError("Some error")))

        // When
        viewModel = HomeViewModel(getLeaguesUseCase, getTeamsUseCase)

        // Then
        assert(!viewModel.leaguesState.value.isLoading)
        assert(viewModel.leaguesState.value.leagues == null)
        assert(viewModel.leaguesState.value.error == "Some error")
    }

    @Test
    fun `Given a list of teams, When fetching the list of teams, Then teams are successfully fetched`() = runTest {
        // Given
        `when`(teamsRepository.getTeamsForLeague("1"))
            .thenReturn(Resource.Success(TeamsFixtures.teamsResult))
        `when`(getLeaguesUseCase.execute())
            .thenReturn(Resource.Success(LeaguesFixtures.leaguesResult))
        `when`(getTeamsUseCase.execute("1"))
            .thenReturn(Resource.Success(TeamsFixtures.teamsResult))

        // When
        viewModel = HomeViewModel(getLeaguesUseCase, getTeamsUseCase)
        viewModel.fetchTeamsForLeague("1")

        // Then
        assert(!viewModel.teamsState.value.isLoading)
        assert(viewModel.teamsState.value.teams == TeamsFixtures.filteredTeams)
    }

    @Test
    fun `Given a random error, When fetching the list of teams, Then fetching fails`() = runTest {
        // Given
        `when`(teamsRepository.getTeamsForLeague("1"))
            .thenReturn(Resource.Error(Failure.NetworkError("Some error")))
        `when`(getLeaguesUseCase.execute())
            .thenReturn(Resource.Success(LeaguesFixtures.leaguesResult))
        `when`(getTeamsUseCase.execute("1"))
            .thenReturn(Resource.Error(Failure.NetworkError("Some error")))

        // When
        viewModel = HomeViewModel(getLeaguesUseCase, getTeamsUseCase)
        viewModel.fetchTeamsForLeague("1")

        // Then
        assert(!viewModel.teamsState.value.isLoading)
        assert(viewModel.teamsState.value.teams == null)
        assert(viewModel.teamsState.value.error == "Some error")
    }
}