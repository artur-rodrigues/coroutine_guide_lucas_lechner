package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1

import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi

class PerformSingleNetworkRequestViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performSingleNetworkRequest() {

    }

    override fun setError(messageError: String) {
        TODO("Not yet implemented")
    }
}