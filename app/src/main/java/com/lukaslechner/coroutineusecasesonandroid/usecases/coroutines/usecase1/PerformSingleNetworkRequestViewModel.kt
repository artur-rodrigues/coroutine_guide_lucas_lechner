package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi

class PerformSingleNetworkRequestViewModel(
    private val mockApi: MockApi = mockApiError()
) : BaseViewModel<UiState>() {

    fun performSingleNetworkRequest() {
        uiState.value = UiState.Loading

        executeLaunchCoroutineRequest {
            val version = mockApi.getRecentAndroidVersions()
            uiState.value = UiState.Success(version)
        }
    }

    override fun setError(messageError: String) {
        uiState.value = UiState.Error(messageError)
    }
}