package com.example.tubes.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tubes.R
import com.example.tubes.model.Quest
import com.example.tubes.model.Restaurant

class QuestAdapter (private var quest: List<Quest>,
                    private val listener: (Quest) -> Unit)
    : RecyclerView.Adapter<QuestAdapter.ViewHolder>() {
    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_quest, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = quest.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(quest[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tMenu: TextView = view.findViewById(R.id.tMenu)
        private val tHarga: TextView = view.findViewById(R.id.tHarga)
        private val tPoin: TextView = view.findViewById(R.id.tPoin)
        private val image: ImageView = view.findViewById(R.id.image)

        fun bindItem(quest: Quest, listener: (Quest) -> Unit, context: Context) {
            tMenu.setText(quest.menu)
            tHarga.setText(quest.harga)
            tPoin.setText(quest.poin)

            Glide.with(context)
                .load(quest.gambar)
                .into(image)

            itemView.setOnClickListener {
                listener(quest)
            }
        }
    }
}