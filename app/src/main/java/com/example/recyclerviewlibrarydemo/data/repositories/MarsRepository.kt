package com.example.recyclerviewlibrarydemo.data.repositories

import com.example.demonetworklibrary.MarsApi
import com.example.recyclerviewlibrarydemo.domain.entities.Property
import com.example.recyclerviewlibrarydemo.domain.repositories.MarsRepositoryI

class MarsRepository : MarsRepositoryI {
    override suspend fun getProperties(filter: String): List<Property> {
        val value = MarsApi.getPropertiesAsync(filter).await()
        return value.map { prop ->
            Property(prop.imgSrcUrl, prop.type)
        }
    }
}