package com.afisdev.common.repository

import com.afisdev.common.model.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by afisdev on 08/09/2023.
 */
abstract class BaseRepository {
    protected suspend fun <R> singleSource(
        networkCall: suspend () -> ResultState<R>
    ): Flow<ResultState<R>> =
        flow {
            emit(ResultState.loading())

            val responseStatus = networkCall()

            if (responseStatus.status == ResultState.Status.SUCCESS) {
                responseStatus.data?.let {
                    emit(ResultState.success(it))
                }
            } else if (responseStatus.status == ResultState.Status.ERROR) {
                emit(ResultState.error<R>(responseStatus.message))
            }

        }.flowOn(Dispatchers.IO)
}