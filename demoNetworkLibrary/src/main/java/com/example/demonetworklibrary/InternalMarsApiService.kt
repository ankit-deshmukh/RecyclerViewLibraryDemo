package com.example.demonetworklibrary

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


internal interface InternalMarsApiService {
    @GET("realestate")
    fun getPropertiesAsync(
        @Query("filter") type: String
    ):
            Deferred<List<MarsProperty>>
}




