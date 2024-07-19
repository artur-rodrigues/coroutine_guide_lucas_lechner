package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        networkRequest()
        println("resul received")
    }

    delay(200)
    job.start()
    job.join()

    println("end of runBlocking")
}

suspend fun networkRequest(): String {
    delay(500)
    return "Result"
}