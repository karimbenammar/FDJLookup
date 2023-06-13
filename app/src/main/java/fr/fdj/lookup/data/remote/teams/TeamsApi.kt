package fr.fdj.lookup.data.remote.teams

import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsApi {
    @GET("search_all_teams.php")
    suspend fun getTeamsForLeague(
        @Query("l") leagueName: String,
    ): TeamsResponse
}