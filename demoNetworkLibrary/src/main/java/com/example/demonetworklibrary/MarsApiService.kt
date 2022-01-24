package com.example.demonetworklibrary

import kotlinx.coroutines.Deferred

interface MarsApiService {
    companion object {
        internal fun createRetrofitInstance() = RetrofitHelper.retrofit.create(InternalMarsApiService::class.java)
    }

    fun getPropertiesAsync(filter: String): Deferred<List<MarsProperty>>
}

object MarsApi : MarsApiService {
    private val marsApiService: InternalMarsApiService by lazy {
        MarsApiService.createRetrofitInstance()
    }

    override fun getPropertiesAsync(filter: String) = marsApiService.getPropertiesAsync(filter)
}