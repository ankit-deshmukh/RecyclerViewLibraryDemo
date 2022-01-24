package com.example.recyclerviewlibrarydemo.domain.usecases

import com.example.recyclerviewlibrarydemo.data.repositories.MarsRepository
import com.example.recyclerviewlibrarydemo.domain.entities.Property
import com.example.recyclerviewlibrarydemo.domain.repositories.MarsRepositoryI

class GetPropertyDetails {
    private val repository: MarsRepositoryI = MarsRepository()

    suspend fun buy(): List<Property> {
        return repository.getProperties("buy")
    }

    suspend fun rent(): List<Property> {
        return repository.getProperties("rent")
    }
    suspend operator fun invoke(): List<Property> {
        return repository.getProperties("")
    }
}