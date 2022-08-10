package com.maolmhuire.kevin.core.entity

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val exception: Throwable? = null) : ResultState<Nothing>
    object Loading : ResultState<Nothing>
}