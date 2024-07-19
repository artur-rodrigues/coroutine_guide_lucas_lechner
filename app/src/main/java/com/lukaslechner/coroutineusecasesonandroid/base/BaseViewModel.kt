package com.lukaslechner.coroutineusecasesonandroid.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

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

    fun <T> executeRxRequest(single: Single<T>, responseCallBack: (T) -> Unit): Disposable {
        return single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseCallBack(it)
            }, {
                setError("Network request failed")
            })
    }

    fun executeLaunchCoroutineRequest(response: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                response()
            } catch (e: Exception) {
                Timber.e(e)
                setError("Network request failed!")
            }
        }
    }
}