package com.example.tubes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Ramen (
    var nama: String ?="",
    var harga: Long ?=0,
    var photo: String ?="",
    var point: Long ?=0

): Parcelable