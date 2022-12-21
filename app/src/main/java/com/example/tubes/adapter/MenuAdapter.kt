package com.example.tubes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tubes.model.Pesanan
import com.example.tubes.model.Ramen
import com.example.tubes.util.Preferences
import com.google.firebase.database.*
import org.w3c.dom.Text
import java.text.NumberFormat
import java.util.*

class MenuAdapter(private var data: List<Ramen>,
                  private val listener:(Ramen) -> Unit)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_menu, parent, false)
        return MenuAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
        var jumlah: Long = 0
        holder.btnCart.setOnClickListener {



            var pesanan = Pesanan()
            pesanan.menu = data[position].nama
            pesanan.nama = holder.preferences.getValues("nama")
            pesanan.point = data[position].point
            pesanan.price = data[position].harga
            pesanan.totalPrice = pesanan.qty!!.times(pesanan.price!!)

            var idPesanan = pesanan.menu.plus("-").plus(pesanan.nama)




//                holder.mDatabaseReference.child(idPesanan).addValueEventListener(object: ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {

            holder.mDatabaseReference.child(idPesanan!!).setValue(pesanan)


            var intent = Intent(contextAdapter.applicationContext, CartActivity::class.java)
            contextAdapter.startActivity(intent)

//
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//
//                }
//            })

            return@setOnClickListener

        }
    }

    private fun getPesanan(){

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mFirebaseInstance = FirebaseDatabase.getInstance()
        val mDatabase = FirebaseDatabase.getInstance().getReference()
        val mDatabaseReference = mFirebaseInstance.getReference("Pesanan")
        var preferences = Preferences(view.context)

        val ivPhoto: ImageView = view.findViewById<ImageView>(R.id.foto)
        val tvNamaMenu: TextView = view.findViewById<TextView>(R.id.tvNamaMenu)
        val tvHarga: TextView = view.findViewById<TextView>(R.id.hargaMenu)
        val btnCart : ImageView = view.findViewById<ImageView>(R.id.btnCart)

        fun bindItem(data: Ramen, listener: (Ramen) -> Unit, context: Context) {
            currency(data.harga!!.toDouble(), tvHarga)
            tvNamaMenu.setText(data.nama)
            Glide.with(context)
                .load(data.photo)
                .into(ivPhoto)

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