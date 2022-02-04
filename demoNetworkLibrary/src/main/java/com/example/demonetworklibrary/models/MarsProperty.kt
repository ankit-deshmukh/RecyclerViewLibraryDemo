package com.example.demonetworklibrary.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MarsProperty(
    val id: String,

    val imgSrcUrl: String,

    val type: String,
    val price: Double): Parcelable {

    val isRental
        get() = type == "rent"

}
