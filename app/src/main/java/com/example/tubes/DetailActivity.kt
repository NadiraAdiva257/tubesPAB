package com.example.tubes

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.tubes.model.Pesanan
import com.example.tubes.signs.SignInActivity
import com.google.firebase.database.*
import java.time.LocalDateTime
import javax.sql.StatementEvent
import kotlin.random.Random
import kotlin.random.nextUInt

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

    lateinit var et_Ramen1: EditText
    lateinit var et_Ramen2: EditText
    lateinit var et_Ramen3: EditText
    lateinit var cb_Ramen1: CheckBox
    lateinit var iv_addRamen2: ImageView
    lateinit var iv_addRamen3: ImageView
    lateinit var et_Add1: EditText
    lateinit var et_Add2: EditText
    lateinit var iv_addAdd1: ImageView
    lateinit var iv_addAdd2: ImageView
    lateinit var et_Drink1: EditText
    lateinit var et_Drink2: EditText
    lateinit var iv_addDrink1: ImageView
    lateinit var iv_addDrink2: ImageView

    lateinit var bPesan: Button

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    lateinit var nama : TextView
    lateinit var dPesanan : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        preferences = com.example.tubes.Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("Pesanan")
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

        et_Ramen1 = findViewById<EditText>(R.id.etRamen1)
        et_Ramen2 = findViewById<EditText>(R.id.etRamen2)
        et_Ramen3 = findViewById<EditText>(R.id.etRamen3)
        cb_Ramen1 = findViewById<CheckBox>(R.id.cbRamen1)
        et_Add1 = findViewById<EditText>(R.id.etAdd1)
        et_Add2 = findViewById<EditText>(R.id.etAdd2)
        et_Drink1 = findViewById<EditText>(R.id.etDrink1)
        et_Drink2 = findViewById<EditText>(R.id.etDrink2)

        bPesan = findViewById<Button>(R.id.Bpesan)


        et_Ramen1.isEnabled = false
        et_Ramen2.isEnabled = false
        et_Ramen3.isEnabled = false
        et_Add1.isEnabled = false
        et_Add2.isEnabled = false
        et_Drink1.isEnabled = false
        et_Drink2.isEnabled = false

        val data = intent.getParcelableExtra<Restaurant>("data")

        tv_nama.text = data?.nama
        tv_alamat.text = data?.alamat
        tv_waktu.text = data?.waktu
        tv_kategori1.text = data?.kategori1
        tv_NamaRamen1.text = data?.ramen1
        tv_HargaRamen1.text = data?.hRamen1.toString()
        tv_NamaRamen2.text = data?.ramen2
        tv_HargaRamen2.text = data?.hRamen2.toString()
        tv_NamaRamen3.text = data?.ramen3
        tv_HargaRamen3.text = data?.hRamen3.toString()

        tv_kategori2.text = data?.kategori2
        tv_Add1.text = data?.additional1
        tv_hAdd1.text = data?.hAdd1.toString()
        tv_Add2.text = data?.additional2
        tv_hAdd2.text = data?.hAdd2.toString()

        tv_kategori3.text = data?.kategori3
        tv_Drink1.text = data?.drink1
        tv_hDrink1.text = data?.hDrink1.toString()
        tv_Drink2.text = data?.drink2
        tv_hDrink2.text = data?.hDrink2.toString()


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

        bPesan.setOnClickListener{

            savePesanan()

            var goCart = Intent(this, Cart)
            startActivity(goCart)

        }
    }


    fun RangkumPesanan(): String {
        var pesanan = ""

        if (cb_Ramen1.isChecked) {
            pesanan += et_Ramen1.text.toString() + " - " + tv_NamaRamen1.text.toString() + "\n"
            //var jPesanan = et_Ramen1 + tv_HargaRamen1
            pesanan += tv_HargaRamen1.text.toString()
        }

        return pesanan
    }

    fun clickRamen1(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            if (checked) {
                et_Ramen1.isEnabled = true
            } else {
                et_Ramen1.isEnabled = false
            }
        }
    }

    private fun savePesanan(){
        var user = User()
        var randomId = Random.nextInt(0,1000)
        var pesanan = Pesanan()
        pesanan.id = randomId.toString()
        pesanan.menu = tv_NamaRamen1.text.toString()
        pesanan.qty = et_Ramen1.text.toString().toLong()
        pesanan.nama = preferences.getValues("nama")
        pesanan.price = tv_HargaRamen1.text.toString().toLong()
        pesanan.totalPrice = pesanan.price!!.times(pesanan.qty!!)

//        user.daftarPesanan = RangkumPesanan()

        mDatabaseReference.child(randomId.toString()).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mDatabaseReference.child(randomId.toString()).setValue(pesanan)
//                preferences.setValues("daftarPesanan", user.daftarPesanan.toString())
                preferences.setValues("status", "1")

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@DetailActivity, databaseError.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }






}