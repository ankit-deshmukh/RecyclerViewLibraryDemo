package com.example.recyclerviewlibrarydemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demonetworklibrary.MarsApi
import com.example.demonetworklibrary.models.MarsProperty
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _propertyList = MutableLiveData<List<MarsProperty>>()
    val propertyList : LiveData<List<MarsProperty>> get() =_propertyList

    private var retrofitService = MarsApi.retrofitService

    init {
        viewModelScope.launch {
            getPropertyData()
        }
    }

    private fun getPropertyData() {
        viewModelScope.launch {
            val deferredList = retrofitService.getProperties("")
            _propertyList.value = deferredList.await()
        }
    }
}