package com.example.tubes.util

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Preferences (val context: Context){
    companion object {
        const val USER_PREF = "USER_PREF"
    }

    private val USER_PREF = "USER_PREF"
    private val sharedPreferences: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(USER_PREF, 0)
        editor = sharedPreferences.edit()
    }

    fun setValues(key : String, value : String) {
        editor.putString(key, value)
            .apply()
    }

    fun getValues(key: String): String? {
        return sharedPreferences.getString(key, "")

    }


}