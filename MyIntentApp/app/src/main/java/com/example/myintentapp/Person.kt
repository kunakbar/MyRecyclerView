package com.example.myintentapp

import android.os.Parcel
import android.os.Parcelable

data class Person {
    val name: String?,
    val age: Int?,
    val email: String?,
    val city: String?
} : Parcelable