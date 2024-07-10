package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main start")
    joinAll(
        async { delayDemonstration(1, 500) },
        async { delayDemonstration(2, 300) }
    )
    println("main end")
}

fun delayDemonstration(number: Int, delay: Long) {
    println("Coroutine $number starts work")
    // Dá erro
    Handler(Looper.getMainLooper())
        .postDelayed({
            println("Coroutine $number has finished")
        }, delay)
}
