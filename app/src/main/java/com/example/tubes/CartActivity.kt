package com.example.tubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tubes.model.Pesanan
import com.google.firebase.database.*
import org.w3c.dom.Text

class CartActivity : AppCompatActivity() {
    lateinit var txNama: TextView
    lateinit var rv_pesanan: RecyclerView

    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    var dataList = ArrayList<Pesanan>()
    lateinit var mDatabaseReference: DatabaseReference
    lateinit var preferences: Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        preferences = com.example.tubes.Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("Pesanan")

        txNama = findViewById(R.id.txNama)
        rv_pesanan = findViewById(R.id.rvPesanan)

        txNama.setText(preferences.getValues("nama"))

        rv_pesanan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getPesanan()
    }

    private fun getPesanan(){
        mDatabaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.children) {
                    var pesanan = getdataSnapshot.getValue(Pesanan::class.java)
                    if (pesanan != null) {
                        println(pesanan.menu)
                    }
                    dataList.add(pesanan!!)
                }

                rv_pesanan.adapter = PesananAdapter(dataList){

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@CartActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}

