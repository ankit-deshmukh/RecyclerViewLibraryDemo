package com.example.recyclerviewlibrarydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.demonetworklibrary.models.MarsProperty

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels<MainViewModel>()

    private lateinit var data: List<MarsProperty>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.propertyList.observe(this) {
            data = it
            Log.d("MainActivity", it.toString())
        }
    }


}