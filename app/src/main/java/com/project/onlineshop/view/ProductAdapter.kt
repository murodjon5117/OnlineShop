package com.project.onlineshop.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.onlineshop.R
import com.project.onlineshop.model.ProductModel
import com.project.onlineshop.screen.productdetail.ProductDetailActivity
import com.project.onlineshop.utils.Costants
import kotlinx.android.synthetic.main.pruduct_item.view.*

class ProductAdapter(val items:List<ProductModel>):RecyclerView.Adapter<ProductAdapter.ItemHolder>() {

    class ItemHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.pruduct_item,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        holder.itemView.setOnClickListener {
            val intent=Intent(it.context,ProductDetailActivity::class.java)
            intent.putExtra(Costants.EXTRA_DATA,item)
            it.context.startActivity(intent)
        }
        Glide.with(holder.itemView.context).load(Costants.HOST_IMAGE+item.image).into(holder.itemView.imgProduct)
        holder.itemView.tvName.text=item.name
        holder.itemView.tvPrice.text=item.price
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}