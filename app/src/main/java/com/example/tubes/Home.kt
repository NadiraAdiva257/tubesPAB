package com.example.tubes

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FillEventHistory
import android.view.View
import android.widget.ImageView

class Home : AppCompatActivity() {
    lateinit var home: ImageView
    lateinit var history: ImageView
    lateinit var quest: ImageView
    lateinit var notifikasi: ImageView
    lateinit var profile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        setContentView(R.layout.activity_home)

        home = findViewById<ImageView>(R.id.ic_home)
        history = findViewById<ImageView>(R.id.ic_history)
        quest = findViewById<ImageView>(R.id.ic_quest)
        notifikasi = findViewById<ImageView>(R.id.ic_notifikasi)
        profile = findViewById<ImageView>(R.id.ic_profile)










    }
}