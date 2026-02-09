package com.example.scalsandroid.scals.state

interface StateValueReading {
    fun getValue(keypath: String): Any?
    fun getArray(keypath: String): List<Any>?
    fun arrayContains(keypath: String, value: Any): Boolean
    fun getArrayCount(keypath: String): Int
}
