package com.example.tubes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference

class DetailActivity : AppCompatActivity() {
    lateinit var tv_nama: TextView
    lateinit var tv_alamat: TextView
    lateinit var tv_waktu: TextView
    lateinit var iv_restaurant: ImageView

    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Restaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tv_nama = findViewById<TextView>(R.id.tvNama)
        tv_alamat = findViewById<TextView>(R.id.tvAlamat)
        tv_waktu = findViewById<TextView>(R.id.tvWaktu)
        iv_restaurant = findViewById<ImageView>(R.id.Irestaurant)

        val data = intent.getParcelableExtra<Restaurant>("data")

        tv_nama.text = data?.nama
        tv_alamat.text = data?.alamat
        tv_waktu.text = data?.waktu

        Glide.with(this)
            .load(data?.foto)
            .into(iv_restaurant)
    }
}