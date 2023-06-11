package fr.fdj.lookup.data.remote.leagues

import retrofit2.http.GET

interface LeaguesApi {
    @GET("all_leagues.php")
    suspend fun getLeagues(): LeaguesResponse
}