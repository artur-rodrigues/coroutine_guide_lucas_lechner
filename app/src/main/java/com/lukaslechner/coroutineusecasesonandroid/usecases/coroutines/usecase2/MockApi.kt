package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2

import com.lukaslechner.coroutineusecasesonandroid.mock.createMockApi
import com.lukaslechner.coroutineusecasesonandroid.utils.MockNetworkInterceptor

fun mockApiSuccess() = createMockApi(
    MockNetworkInterceptor()
        .mockVersionSuccess()
        .mockFeatureSuccess()
)

fun mockApiError() =
    createMockApi(
        MockNetworkInterceptor()
            .mockVersionError()
            .mockFeatureError()
    )