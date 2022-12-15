package com.example.tubes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import org.w3c.dom.Text

class CartActivity : AppCompatActivity() {
    lateinit var txPesanan: TextView
    lateinit var txNama: TextView

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        txPesanan = findViewById(R.id.txPesanan)
        txNama = findViewById(R.id.txNama)

        txPesanan.text = User.daftarPesanan

        val nama = txNama.text.toString().trim()
        val daftarPesanan = txPesanan.text.toString().trim()


    }
}

