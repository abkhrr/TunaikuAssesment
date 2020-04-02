package com.adyabukhari.tunaikuasessment.data.repositories

sealed class Response<T> {
    data class Success<T>(val response: T) : Response<T>()
    data class Error<T>(val code: Int, val message: String) : Response<T>()
}