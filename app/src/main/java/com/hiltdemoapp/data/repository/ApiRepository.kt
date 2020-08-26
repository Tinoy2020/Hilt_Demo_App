package com.hiltdemoapp.data.repository

import com.hiltdemoapp.data.api.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(page: String) = apiService.getUsers(page)

}