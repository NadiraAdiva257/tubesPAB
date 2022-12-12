package com.example.tubes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant (
    var nama:String ?= "",
    var foto:String ?= "",
    var waktu:String ?= "",
    var alamat:String ?= "",

):Parcelable