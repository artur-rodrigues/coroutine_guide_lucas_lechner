package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlin.concurrent.thread

fun main() {
    println("main start")
    threadRoutine(1, 500)
    threadRoutine(2, 300)
    Thread.sleep(600)
    println("main end")
}

fun threadRoutine(number: Int, delay: Long) {
    thread {
        println("ThreadRoutine $number starts work")
        Thread.sleep(delay)
        println("ThreadRoutine $number has finished")
    }
}