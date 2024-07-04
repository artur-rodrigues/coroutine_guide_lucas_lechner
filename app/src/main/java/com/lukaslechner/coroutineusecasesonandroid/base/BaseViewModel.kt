package com.lukaslechner.coroutineusecasesonandroid.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseViewModel<T> : ViewModel() {

    fun uiState(): LiveData<T> = uiState
    protected val uiState: MutableLiveData<T> = MutableLiveData()

    abstract fun setError(messageError: String)

    fun <T> executeCallRequest(call: Call<T>, responseCallBack: (T) -> Unit) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(callResponse: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    responseCallBack(response.body()!!)
                } else {
                    setError("Network request failed!")
                }
            }

            override fun onFailure(callResponse: Call<T>, error: Throwable) {
                setError("Something unexpected happened!")
            }
        })
    }
}