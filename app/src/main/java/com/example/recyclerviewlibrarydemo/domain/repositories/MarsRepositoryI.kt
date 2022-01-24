package com.example.recyclerviewlibrarydemo.domain.repositories

import com.example.recyclerviewlibrarydemo.R
import com.example.recyclerviewlibrarydemo.domain.entities.Property

interface MarsRepositoryI {
    suspend fun getProperties(filter: String): List<Property>
}