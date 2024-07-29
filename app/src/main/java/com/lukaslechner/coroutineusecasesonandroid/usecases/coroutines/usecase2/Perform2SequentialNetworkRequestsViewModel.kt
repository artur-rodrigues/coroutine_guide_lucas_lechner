package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi

class Perform2SequentialNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApiSuccess()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        executeLaunchCoroutineRequest {
            val versions = mockApi.getRecentAndroidVersions()
            val versionFeatures = mockApi.getAndroidVersionFeatures(versions.last().apiLevel)
            uiState.value = UiState.Success(versionFeatures)
        }
    }

    override fun setError(messageError: String) {
        uiState.value = UiState.Error(messageError)
    }
}