package com.sangeetha.contacts.util

sealed class NetworkResource<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Success<T>(data: T) : NetworkResource<T>(data)
    class Loading<T>(data: T? = null) : NetworkResource<T>(data)
    class Failure<T>(message: String, data: T? = null) : NetworkResource<T>(data, message)
}
