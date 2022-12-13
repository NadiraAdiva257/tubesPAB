package com.example.tubes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetailActivity : AppCompatActivity() {
    lateinit var tv_nama: TextView
    lateinit var tv_alamat: TextView
    lateinit var tv_waktu: TextView
    lateinit var iv_restaurant: ImageView
    lateinit var tv_kategori1: TextView
    lateinit var iv_ramen1: ImageView
    lateinit var tv_NamaRamen1: TextView
    lateinit var tv_HargaRamen1: TextView
    lateinit var iv_ramen2: ImageView
    lateinit var tv_NamaRamen2: TextView
    lateinit var tv_HargaRamen2: TextView
    lateinit var iv_ramen3: ImageView
    lateinit var tv_NamaRamen3: TextView
    lateinit var tv_HargaRamen3: TextView

    lateinit var tv_kategori2: TextView
    lateinit var iv_Add1: ImageView
    lateinit var tv_Add1: TextView
    lateinit var tv_hAdd1: TextView
    lateinit var iv_Add2: ImageView
    lateinit var tv_Add2: TextView
    lateinit var tv_hAdd2: TextView

    lateinit var tv_kategori3: TextView
    lateinit var iv_Drink1: ImageView
    lateinit var tv_Drink1: TextView
    lateinit var tv_hDrink1: TextView
    lateinit var iv_Drink2: ImageView
    lateinit var tv_Drink2: TextView
    lateinit var tv_hDrink2: TextView


    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Restaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tv_nama = findViewById<TextView>(R.id.tvNama)
        tv_alamat = findViewById<TextView>(R.id.tvAlamat)
        tv_waktu = findViewById<TextView>(R.id.tvWaktu)
        iv_restaurant = findViewById<ImageView>(R.id.Irestaurant)
        tv_kategori1 = findViewById<TextView>(R.id.tvKategori1)
        iv_ramen1 = findViewById<ImageView>(R.id.ivRamen1)
        tv_NamaRamen1 = findViewById<TextView>(R.id.tvNamaRamen1)
        tv_HargaRamen1 = findViewById<TextView>(R.id.tvHargaRamen1)
        iv_ramen2 = findViewById<ImageView>(R.id.ivRamen2)
        tv_NamaRamen2 = findViewById<TextView>(R.id.tvNamaRamen2)
        tv_HargaRamen2 = findViewById<TextView>(R.id.tvHargaRamen2)
        iv_ramen3 = findViewById<ImageView>(R.id.ivRamen3)
        tv_NamaRamen3 = findViewById<TextView>(R.id.tvNamaRamen3)
        tv_HargaRamen3 = findViewById<TextView>(R.id.tvHargaRamen3)

        tv_kategori2 = findViewById<TextView>(R.id.tvKategori2)
        iv_Add1 = findViewById<ImageView>(R.id.ivAddition1)
        tv_Add1 = findViewById<TextView>(R.id.tvNamaAdd1)
        tv_hAdd1 = findViewById<TextView>(R.id.tvHargaAdd1)
        iv_Add2 = findViewById<ImageView>(R.id.ivAddition2)
        tv_Add2 = findViewById<TextView>(R.id.tvNamaAdd2)
        tv_hAdd2 = findViewById<TextView>(R.id.tvHargaAdd2)

        tv_kategori2 = findViewById<TextView>(R.id.tvKategori2)
        iv_Add1 = findViewById<ImageView>(R.id.ivAddition1)
        tv_Add1 = findViewById<TextView>(R.id.tvNamaAdd1)
        tv_hAdd1 = findViewById<TextView>(R.id.tvHargaAdd1)
        iv_Add2 = findViewById<ImageView>(R.id.ivAddition2)
        tv_Add2 = findViewById<TextView>(R.id.tvNamaAdd2)
        tv_hAdd2 = findViewById<TextView>(R.id.tvHargaAdd2)

        tv_kategori3 = findViewById<TextView>(R.id.tvKategori3)
        iv_Drink1 = findViewById<ImageView>(R.id.ivDrink1)
        tv_Drink1 = findViewById<TextView>(R.id.tvDrink1)
        tv_hDrink1 = findViewById<TextView>(R.id.tvHargaDrink1)
        iv_Drink2 = findViewById<ImageView>(R.id.ivDrink2)
        tv_Drink2 = findViewById<TextView>(R.id.tvDrink2)
        tv_hDrink2 = findViewById<TextView>(R.id.tvHargaDrink2)

        val data = intent.getParcelableExtra<Restaurant>("data")

        tv_nama.text = data?.nama
        tv_alamat.text = data?.alamat
        tv_waktu.text = data?.waktu
        tv_kategori1.text = data?.kategori1
        tv_NamaRamen1.text = data?.ramen1
        tv_HargaRamen1.text = data?.hRamen1
        tv_NamaRamen2.text = data?.ramen2
        tv_HargaRamen2.text = data?.hRamen2
        tv_NamaRamen3.text = data?.ramen3
        tv_HargaRamen3.text = data?.hRamen3

        tv_kategori2.text = data?.kategori2
        tv_Add1.text = data?.additional1
        tv_hAdd1.text = data?.hAdd1
        tv_Add2.text = data?.additional2
        tv_hAdd2.text = data?.hAdd2

        tv_kategori3.text = data?.kategori3
        tv_Drink1.text = data?.drink1
        tv_hDrink1.text = data?.hDrink1
        tv_Drink2.text = data?.drink2
        tv_hDrink2.text = data?.hDrink2


        Glide.with(this)
            .load(data?.foto)
            .into(iv_restaurant)

        Glide.with(this)
            .load(data?.pRamen1)
            .into(iv_ramen1)

        Glide.with(this)
            .load(data?.pRamen2)
            .into(iv_ramen2)

        Glide.with(this)
            .load(data?.pRamen3)
            .into(iv_ramen3)

        Glide.with(this)
            .load(data?.pAdd1)
            .into(iv_Add1)

        Glide.with(this)
            .load(data?.pAdd2)
            .into(iv_Add2)

        Glide.with(this)
            .load(data?.pDrink1)
            .into(iv_Drink1)

        Glide.with(this)
            .load(data?.pDrink2)
            .into(iv_Drink2)

    }
}