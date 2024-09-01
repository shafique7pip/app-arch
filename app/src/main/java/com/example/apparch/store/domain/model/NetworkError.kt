package com.example.apparch.store.domain.model

data class NetworkError(
    val error: ApiError,
    val t: Throwable? = null
)

enum class ApiError(val message: String) {
    NetworkError("Network Error"),
    UnknownError("Unknown Error"),
    UnknownResponse("Unknown Response")
}