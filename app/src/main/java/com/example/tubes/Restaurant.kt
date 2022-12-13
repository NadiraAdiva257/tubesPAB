package com.example.tubes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant (
    var nama:String ?= "",
    var foto:String ?= "",
    var waktu:String ?= "",
    var alamat:String ?= "",
    var kategori1:String ?= "",
    var ramen1:String ?= "",
    var hRamen1:String ?= "",
    var pRamen1:String ?= "",
    var ramen2:String ?= "",
    var hRamen2:String ?= "",
    var pRamen2:String ?= "",
    var ramen3:String ?= "",
    var hRamen3:String ?= "",
    var pRamen3:String ?= "",
    var kategori2:String ?= "",
    var additional1:String ?= "",
    var additional2:String ?= "",
    var pAdd1:String ?= "",
    var pAdd2:String ?= "",
    var hAdd1:String ?= "",
    var hAdd2:String ?= "",
    var kategori3:String ?= "",
    var drink1:String ?= "",
    var pDrink1:String ?= "",
    var hDrink1:String ?= "",
    var drink2:String ?= "",
    var pDrink2:String ?= "",
    var hDrink2:String ?= "",

    ):Parcelable