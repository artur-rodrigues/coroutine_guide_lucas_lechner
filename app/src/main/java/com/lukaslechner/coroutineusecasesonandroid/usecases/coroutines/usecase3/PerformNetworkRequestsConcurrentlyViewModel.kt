package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading

        executeLaunchCoroutineRequest {
            val oreoFeature = mockApi.getAndroidVersionFeatures(27)
            val pieFeature = mockApi.getAndroidVersionFeatures(28)
            val android10Feature = mockApi.getAndroidVersionFeatures(29)

            val versionFeature = listOf(oreoFeature, pieFeature, android10Feature)

            uiState.value = UiState.Success(versionFeature)
        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading
    }

    override fun setError(messageError: String) {
        uiState.value = UiState.Error(messageError)
    }
}