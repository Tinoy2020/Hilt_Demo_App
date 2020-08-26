package com.hiltdemoapp.data.api

import com.hiltdemoapp.data.model.UsersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: String): Response<UsersList>

}