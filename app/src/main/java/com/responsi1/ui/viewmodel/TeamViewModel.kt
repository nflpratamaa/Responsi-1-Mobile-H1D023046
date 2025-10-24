package com.responsi1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.responsi1.data.model.TeamResponse
import com.responsi1.data.repository.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val repository = TeamRepository()

    private val _teamData = MutableLiveData<TeamResponse>()
    val teamData: LiveData<TeamResponse> = _teamData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchTeamData() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val result = repository.getTeamDetails(57) // Arsenal = ID 57
                result.onSuccess { data ->
                    _teamData.postValue(data)
                }.onFailure { e ->
                    _errorMessage.postValue("Terjadi kesalahan: ${e.message}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Terjadi kesalahan: ${e.message}")
            }
            _isLoading.postValue(false)
        }
    }
}
