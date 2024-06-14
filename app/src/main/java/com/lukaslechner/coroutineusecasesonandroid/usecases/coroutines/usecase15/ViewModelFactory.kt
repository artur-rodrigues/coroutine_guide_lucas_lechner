package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase15

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager

class ViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(WorkManager::class.java)
            .newInstance(WorkManager.getInstance(context))
    }
}