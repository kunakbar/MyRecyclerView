package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_vegetable.view.*

class ListVegetableAdapter(private val listVegetable: ArrayList<Vegetable>) :
    RecyclerView.Adapter<ListVegetableAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(vegetable: Vegetable) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(
                        resources.getIdentifier(
                            vegetable.photo,
                            "drawable",
                            itemView.context.packageName
                        )
                    ) //MENAMBAHKAN KODE SENDIRI KARENA
                    //KALO BUKAN PAKE INTERNET(URL) ADALAH DENGAN Glide.with(this).load(R.drawable.my_drawable_image_name).into(myImageView);
                    //KARENA TIDAK BISA MEMASUKAN R.drawable ke res string maka caranya dengan mencari nama yang sama, yang ada di res drawable
                    // dari tanya orang
                    .apply(RequestOptions().override(55, 55))
                    .into(img_item_photo)

                tv_item_name.text =
                    vegetable.name //mengubah nama sayuran pada row xml dengan Vegetable.kt
                tv_item_description.text = vegetable.description

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(vegetable) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Vegetable)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_vegetable, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listVegetable.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listVegetable[position])
    }
}