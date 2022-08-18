package com.sangeetha.contacts.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if(shouldFetch(data)) {
        emit(NetworkResource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { NetworkResource.Success(it) }
        } catch (e: Exception) {
            query().map { NetworkResource.Failure( message = e.localizedMessage ?: "Something went wrong", data = it) }
        }
    } else {
        query().map {
            NetworkResource.Success(it)
        }
    }
    emitAll(flow)
}