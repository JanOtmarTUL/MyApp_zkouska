package com.example.myapp_zkouska

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StaniceAdapter(private val data: List<dbStanice>) : RecyclerView.Adapter<StaniceAdapter.StaniceViewHolder>() {

    class StaniceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stkNazev: TextView = itemView.findViewById(R.id.tvStkNazev)
        val uliceStanice: TextView = itemView.findViewById(R.id.tvUlice)
        val mestoStanice: TextView = itemView.findViewById(R.id.tvMesto)
        val telStanice: TextView = itemView.findViewById(R.id.tvTelefon)
        val emailStanice: TextView = itemView.findViewById(R.id.tvEmail)
        val webStanice: TextView = itemView.findViewById(R.id.tvWeb)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaniceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stk_item, parent, false)
        return StaniceViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaniceViewHolder, position: Int) {
        val currentItem = data[position]
        holder.stkNazev.text = currentItem.stkNazev
        holder.uliceStanice.text = currentItem.ulice
        holder.mestoStanice.text = currentItem.mesto
        holder.telStanice.text = currentItem.telefon
        holder.emailStanice.text = currentItem.email
        holder.webStanice.text = currentItem.web

    }

    override fun getItemCount() = data.size
}
