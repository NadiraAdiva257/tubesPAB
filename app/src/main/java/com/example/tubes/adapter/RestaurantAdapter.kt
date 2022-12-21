package com.example.tubes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tubes.R
import com.example.tubes.model.Restaurant

class RestaurantAdapter(private var data: List<Restaurant>,
                        private val listener:(Restaurant) -> Unit)
    : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
        lateinit var contextAdapter: Context

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            contextAdapter = parent.context
            val inflatedView = layoutInflater.inflate(R.layout.row_item_restaurant, parent, false)
            return ViewHolder(inflatedView)
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tvNama:TextView = view.findViewById(R.id.tvTokoRamen)
        private val tvJam:TextView = view.findViewById(R.id.tvJamToko)
        private val tvLok:TextView = view.findViewById(R.id.tvLokToko)
        private val tvImage:ImageView = view.findViewById(R.id.ivTokoRamen)

        fun bindItem(data: Restaurant, listener: (Restaurant) -> Unit, context: Context) {
            tvNama.setText(data.nama)
            tvJam.setText(data.waktu)
            tvLok.setText(data.alamat)

            Glide.with(context)
                .load(data.photo)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }


    }
