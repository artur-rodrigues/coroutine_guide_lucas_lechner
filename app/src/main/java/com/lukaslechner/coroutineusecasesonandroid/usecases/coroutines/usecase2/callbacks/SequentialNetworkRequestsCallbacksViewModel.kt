package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import retrofit2.Call

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private var versionCall: Call<List<AndroidVersion>>? = null
    private var featureCall: Call<VersionFeatures>? = null

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        versionCall = mockApi.getRecentAndroidVersions()

        executeCallRequest(versionCall!!) { list ->
            featureCall = mockApi.getAndroidVersionFeatures(list.last().apiLevel)
            executeCallRequest(featureCall!!) {
                uiState.value = UiState.Success(it)
            }
        }
    }

    override fun setError(messageError: String) {
        uiState.value = UiState.Error(messageError)
    }

    override fun onCleared() {
        super.onCleared()

        versionCall?.cancel()
        featureCall?.cancel()
    }
}