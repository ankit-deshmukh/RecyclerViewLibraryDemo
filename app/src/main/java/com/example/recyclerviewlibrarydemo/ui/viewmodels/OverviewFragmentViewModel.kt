package com.example.recyclerviewlibrarydemo.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewlibrarydemo.domain.entities.Property
import com.example.recyclerviewlibrarydemo.domain.usecases.GetPropertyDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OverviewFragmentViewModel : ViewModel() {
    private val TAG = "OverviewFragmentVM"

    val marsProperties = MutableLiveData<List<Property>>()
    val filters = MutableLiveData<List<String>>()
    val showIndicator = MutableLiveData<Boolean>()

    val getPropertyDetails = GetPropertyDetails()

    init {
        showIndicator.value = false
    }

    fun addFilter(name: String) {
        if (filters.value?.contains(name) == true) return
        if (filters.value == null) filters.value = listOf<String>()
        filters.value = filters.value!! + name
        getProperties()
    }

    fun removeFilter(name: String) {
        if (filters.value == null) return
        if (filters.value?.contains(name) == false) return
        filters.value = filters.value!! - name
        getProperties()
    }

    fun getProperties() {
        Log.d(TAG, "getProperties: filters : ${filters.value}")
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                showIndicator.value = true
            }
            val value = when {
                filters.value?.size == 2 -> getPropertyDetails()
                filters.value?.contains("Rent") == true -> getPropertyDetails.rent()
                filters.value?.contains("Buy") == true -> getPropertyDetails.buy()
                else -> listOf()
            }
            withContext(Dispatchers.Main) {
                showIndicator.value = false
            }
            Log.d(TAG, "onCreate: $value")
            withContext(Dispatchers.Main) {
                marsProperties.value = value
            }
        }
    }
}

