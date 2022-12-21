package com.example.tubes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tubes.model.Pesanan
import com.example.tubes.util.Preferences
import com.google.firebase.database.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CartActivity : AppCompatActivity() {
    lateinit var tvTotal: TextView
    lateinit var rv_pesanan: RecyclerView
    lateinit var btn_bayar : Button

    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    var dataList = ArrayList<Pesanan>()
    lateinit var mDatabaseReference: DatabaseReference
    lateinit var preferences: Preferences


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("Pesanan")

        getPesanan()


        rv_pesanan = findViewById(R.id.rvPesanan)
        tvTotal = findViewById(R.id.tvTotalBayar)
        btn_bayar = findViewById(R.id.btn_bayar)

        btn_bayar.setOnClickListener {

            for (pesanan in dataList) {
                var pointUser = preferences.getValues("poin")?.toLong()
                pointUser = pointUser?.plus(pesanan.point!!)
                preferences.setValues("poin",pointUser.toString())


                var idPesanan = pesanan?.menu.plus("-").plus(pesanan?.nama)

                mDatabaseReference.child(idPesanan).removeValue()
            }



        }



        rv_pesanan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun getPesanan(){
        mDatabaseReference.orderByChild("nama").equalTo(preferences.getValues("nama")).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val list = dataSnapshot.children.map {
                        dataSnapshot ->  dataSnapshot.getValue(Pesanan::class.java)
                }.filter {
                        pesanan ->  "pending".equals(pesanan!!.status)
                }

                var totalbayar:Long = 0

                dataList.clear()
                for (getdataSnapshot in list) {

                    if (getdataSnapshot != null) {
                        println(getdataSnapshot.menu)
                        totalbayar+= getdataSnapshot.totalPrice!!
                    }
                    dataList.add(getdataSnapshot!!)
                }

                currency(totalbayar.toDouble(), tvTotal)


                rv_pesanan.adapter = PesananAdapter(dataList){

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@CartActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }


    fun bayar(){




    }

    private fun currency(harga: Double, textView: TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }
}