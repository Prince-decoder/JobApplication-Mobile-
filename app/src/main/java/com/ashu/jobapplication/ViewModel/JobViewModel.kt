package com.ashu.jobapplication.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.jobapplication.Model.JobData
import com.ashu.jobapplication.Service.ReteroService
import kotlinx.coroutines.launch

data class ServerStatus(
    val loading: Boolean=true,
    val list: List<JobData> = emptyList(),
    val error: String?= null
)

class JobViewModel : ViewModel() {
    private val _status= mutableStateOf(ServerStatus())

    val status: State<ServerStatus> = _status

    init {
        getAllJobs()
    }

    fun getAllJobs()
    {
        viewModelScope.launch {
            try {
                val response = ReteroService.getAllJobs()
                _status.value = _status.value.copy(
                    loading = false,
                    list = response._embedded.jobPosts
                )
            }catch (e: Exception)
            {
                _status.value=_status.value.copy(
                    loading = false,
                    error = e.message
                )
            }
        }
    }
}