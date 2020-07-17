package com.raka.qtest.view.ui.productlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raka.qtest.R
import com.raka.qtest.data.model.ProductListCompact
import kotlinx.android.synthetic.main.item_list.view.*

class ProductListAdapter:RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    var list:MutableList<ProductListCompact> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_list,parent,false)
        return ProductListViewHolder(
            itemView
        )
    }
    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
    class ProductListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(item:ProductListCompact){
            Glide.with(itemView).load(item.thumbnail).into(itemView.iv_item_list)
            itemView.tv_item_name.text = item.productName
            itemView.tv_item_stok_number.text = item.stock.toString()
            itemView.tv_item_price.text = itemView.context.getString(R.string.item_price,item.price.toString())
            itemView.setOnClickListener {
                val bundle = bundleOf("id" to item.productId)
                it.findNavController().navigate(R.id.action_productListFragment_to_productDetailFragment,bundle)
            }
        }
    }
    fun updateData(data:MutableList<ProductListCompact>){
        this.list.addAll(data)
        notifyDataSetChanged()
    }
}