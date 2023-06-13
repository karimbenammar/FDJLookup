package fr.fdj.lookup.data.remote.teams

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("teams") val teams: List<ApiTeam>?
) {
    data class ApiTeam(
        @SerializedName("idTeam") val id: String,
        @SerializedName("strTeam") val name: String,
        @SerializedName("strSport") val sport: String,
        @SerializedName("strLeague") val league: String,
        @SerializedName("strAlternate") val nameAlt: String,
        @SerializedName("strTeamBadge") val badgeUrl: String,
    )
}
