package com.capstoneproject2.core.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Success<T>(data: T): Resource<T>(data)
    class Loading<T>(data: T? = null): Resource<T>(data)
}