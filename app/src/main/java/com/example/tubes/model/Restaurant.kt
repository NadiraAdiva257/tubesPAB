package com.example.tubes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant (
    var nama:String ?= "",
    var photo:String ?= "",
    var waktu:String ?= "",
    var alamat:String ?= "",
    var kategori1:String ?= "",
    var ramen1:String ?= "",
    var hRamen1:Long ?= 0,
    var pRamen1:String ?= "",
    var ramen2:String ?= "",
    var hRamen2:Long ?= 0,
    var pRamen2:String ?= "",
    var ramen3:String ?= "",
    var hRamen3:Long ?= 0,
    var pRamen3:String ?= "",
    var kategori2:String ?= "",
    var additional1:String ?= "",
    var additional2:String ?= "",
    var pAdd1:String ?= "",
    var pAdd2:String ?= "",
    var hAdd1:Long ?= 0,
    var hAdd2:Long ?= 0,
    var kategori3:String ?= "",
    var drink1:String ?= "",
    var pDrink1:String ?= "",
    var hDrink1:Long ?= 0,
    var drink2:String ?= "",
    var pDrink2:String ?= "",
    var hDrink2:Long ?= 0,

    ):Parcelable