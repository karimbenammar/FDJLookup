package fr.fdj.lookup.data.remote.leagues

import com.google.gson.annotations.SerializedName

data class LeaguesResponse(
    @SerializedName("leagues") val leagues: List<ApiLeague>
) {
    data class ApiLeague(
        @SerializedName("idLeague") val id: String,
        @SerializedName("strLeague") val name: String,
        @SerializedName("strSport") val sport: String,
        @SerializedName("strLeagueAlternate") val nameAlt: String?,
    )
}
