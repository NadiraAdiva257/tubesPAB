package com.example.tubes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tubes.model.Pesanan
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.NumberFormat
import java.util.*

class PesananAdapter(private var data: List<Pesanan>,
                     private val listener:(Pesanan) -> Unit)
    : RecyclerView.Adapter<PesananAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PesananAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_cart, parent, false)
        return PesananAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PesananAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
        holder.tvJumlah.setText(data[position].qty.toString())

        holder.btnAdd.setOnClickListener {
            var jumlah = data[position].qty!! + 1
            var idPesanan = data[position].menu.plus("-").plus(data[position].nama)
            holder.mDatabaseReference.child(idPesanan).child("qty").setValue(jumlah).addOnSuccessListener {
                println("berhasil ubah data")
            }
                .addOnFailureListener {
                    println(it.message)
                }
            holder.mDatabaseReference.child(idPesanan).child("totalPrice").setValue(jumlah.times(
                data[position].price!!
            ))

            holder.tvJumlah.setText(jumlah.toString())

            return@setOnClickListener

        }

        holder.btnMin.setOnClickListener {
            var idPesanan = data[position].menu.plus("-").plus(data[position].nama)

            if (data[position].qty!!.toInt() == 1){
                holder.mDatabaseReference.child(idPesanan).removeValue()

            }
            var jumlah = data[position].qty!! - 1
            holder.mDatabaseReference.child(idPesanan).child("qty").setValue(jumlah)
            holder.mDatabaseReference.child(idPesanan).child("totalPrice").setValue(jumlah.times(
                data[position].price!!
            ))

            holder.tvJumlah.setText(jumlah.toString())

            return@setOnClickListener

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mFirebaseInstance = FirebaseDatabase.getInstance()
        val mDatabase = FirebaseDatabase.getInstance().getReference()
        val mDatabaseReference = mFirebaseInstance.getReference("Pesanan")

        val ivPhoto: ImageView = view.findViewById<ImageView>(R.id.fotoMenuCart)
        val tvNamaMenu: TextView = view.findViewById<TextView>(R.id.tvNamaMenuCart)
        val tvHarga: TextView = view.findViewById<TextView>(R.id.hargaMenuCart)
        val btnAdd : ImageView = view.findViewById<ImageView>(R.id.btn_tambah)
        val btnMin : ImageView = view.findViewById<ImageView>(R.id.btn_kurang)
        val tvJumlah: TextView = view.findViewById(R.id.tvJumlahPesanan)




        fun bindItem(data: Pesanan, listener: (Pesanan) -> Unit, context: Context) {
            tvNamaMenu.setText(data.menu)
            tvJumlah.setText(data.qty.toString())
            currency(data.totalPrice?.toDouble()!!, tvHarga)

            itemView.setOnClickListener {
                listener(data)
            }
        }

        private fun currency(harga: Double, textView: TextView) {
            val localID = Locale("in", "ID")
            val format = NumberFormat.getCurrencyInstance(localID)
            textView.setText(format.format(harga))
        }
    }


}