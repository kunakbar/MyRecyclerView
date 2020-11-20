package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_grid_vegetable.view.*

class GridVegetableAdapter (private val listVegetable: ArrayList<Vegetable>) : RecyclerView.Adapter<GridVegetableAdapter.GridViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_grid_vegetable, viewGroup, false)
        return GridViewHolder(view)
    }
    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listVegetable[position])
    }
    override fun getItemCount(): Int = listVegetable.size
    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(vegetable: Vegetable) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(resources.getIdentifier(vegetable.photo,"drawable", itemView.context.packageName))
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(vegetable) }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Vegetable)
    }
}