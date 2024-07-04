package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2.rx

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import io.reactivex.disposables.Disposable

class SequentialNetworkRequestsRxViewModel(
    private val mockApi: RxMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private var disposable: Disposable? = null

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        val single = mockApi.getRecentAndroidVersions()
            .flatMap { androidVersion ->
                val recentVersion = androidVersion.last()
                mockApi.getAndroidVersionFeatures(recentVersion.apiLevel)
            }

        disposable = executeRxRequest(single) {
            uiState.value = UiState.Success(it)
        }
    }

    override fun setError(messageError: String) {
        uiState.value = UiState.Error(messageError)
    }

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }
}