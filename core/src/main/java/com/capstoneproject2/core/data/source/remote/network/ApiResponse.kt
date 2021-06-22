package com.capstoneproject2.core.data.source.remote.network

sealed class ApiResponse<out T> {

    object Empty: ApiResponse<Nothing>()
    data class Success<out T>(val data: T): ApiResponse<T>()
    data class Error(val errorMessage: String): ApiResponse<Nothing>()

}