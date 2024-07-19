package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1

import com.lukaslechner.coroutineusecasesonandroid.mock.createMockApi
import com.lukaslechner.coroutineusecasesonandroid.utils.MockNetworkInterceptor

fun mockApiSuccess() =
    createMockApi(
        MockNetworkInterceptor()
            .mockVersionSuccess()
    )

fun mockApiError() =
    createMockApi(
        MockNetworkInterceptor()
            .mockVersionError()
    )