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
import com.google.firebase.database.ValueEventListener

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
        val inflatedView = layoutInflater.inflate(R.layout.row_item_pesanan, parent, false)
        return PesananAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PesananAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvMenu: TextView = view.findViewById(R.id.tvMenu)
        private val tvJumlah: TextView = view.findViewById(R.id.tvJumlah)
        private val tvTotal: TextView = view.findViewById(R.id.tvTotal)

        fun bindItem(data: Pesanan, listener: (Pesanan) -> Unit, context: Context) {
            tvMenu.setText(data.menu)
            tvJumlah.setText(data.qty.toString())
            tvTotal.setText(data.totalPrice.toString())

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
