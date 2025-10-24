package com.responsi1.data.repository

import com.responsi1.data.api.ApiConfig
import com.responsi1.data.model.TeamResponse

class TeamRepository {
    private val apiService = ApiConfig.getApiService()

    suspend fun getTeamDetails(teamId: Int): Result<TeamResponse> {
        return try {
            val response = apiService.getTeamDetails(teamId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}