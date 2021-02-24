package com.harsh.databindingwithrecyclerview.data

sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : APIResult<T>()
    data class Error(val exception: Exception) : APIResult<Nothing>()
}