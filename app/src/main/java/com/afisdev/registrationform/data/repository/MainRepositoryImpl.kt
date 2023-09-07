package com.afisdev.registrationform.data.repository

import com.afisdev.common.repository.BaseRepository
import com.afisdev.registrationform.data.remote.datasource.MainDataSource
import javax.inject.Inject


/**
 * Created by afisdev on 08/09/2023.
 */
class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
): BaseRepository(), MainRepository {

    override suspend fun getProvince() = singleSource {
        mainDataSource.getProvince()
    }
}