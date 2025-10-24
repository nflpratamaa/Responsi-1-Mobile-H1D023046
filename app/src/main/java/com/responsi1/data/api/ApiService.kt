package com.responsi1.data.api

import com.responsi1.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("teams/{id}")
    suspend fun getTeamDetails(@Path("id") teamId: Int): Response<TeamResponse>
}