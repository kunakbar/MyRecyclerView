package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_cardview_vegetable.view.*

class CardViewVegetableAdapter (private val listVegetable: ArrayList<Vegetable>) : RecyclerView.Adapter<CardViewVegetableAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cardview_vegetable, viewGroup, false)
        return CardViewViewHolder(view)
    }
    override fun getItemCount(): Int = listVegetable.size
    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listVegetable[position])
    }
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(vegetable: Vegetable) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(resources.getIdentifier(vegetable.photo,"drawable", itemView.context.packageName))
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)

                tv_item_name.text = vegetable.name
                tv_item_description.text = vegetable.description
                btn_set_favorite.setOnClickListener { Toast.makeText(itemView.context, "Favorite ${vegetable.name}", Toast.LENGTH_SHORT).show() }
                btn_set_share.setOnClickListener { Toast.makeText(itemView.context, "Share ${vegetable.name}", Toast.LENGTH_SHORT).show() }
                itemView.setOnClickListener { Toast.makeText(itemView.context, "Kamu memilih ${vegetable.name}", Toast.LENGTH_SHORT).show() }
            }
        }
    }
}