package com.example.recyclerviewlibrarydemo.domain.entities

import android.graphics.Color
import android.util.Log
import androidx.annotation.ColorInt
import com.example.recyclerviewlibrarydemo.R

data class Property(val imageUrl: String?, val type: String) {

    fun getColor(): Int {
        val color = when (type.trim()) {
            "buy" -> Color.GREEN
            "rent" -> Color.YELLOW
            else -> Color.LTGRAY
        }
        return color
    }
}