package com.example.inventorymanagementsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorymanagementsystem.databinding.CardItemStd1Binding
import com.example.inventorymanagementsystem.databinding.CardItemStdBinding
import com.example.inventorymanagementsystem.interfac.onItemClick
import com.example.inventorymanagementsystem.model.Product
import kotlinx.coroutines.NonDisposableHandle.parent

class CustomerProductAdapter:RecyclerView.Adapter<CustomerProductAdapter.CustomerProductViewHolder>() {
    private var stdList:ArrayList<Product> = ArrayList()
    private var onClickItem:((Product)-> Unit)?=null
    private var onClickAddItem:((Product)-> Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerProductViewHolder {
        val view=CardItemStd1Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CustomerProductViewHolder(view)
    }
    fun setOnClickItem(callback:(Product)->Unit){
        this.onClickItem=callback
    }
    fun setOnClickAddItem(callback: (Product) -> Unit){
        this.onClickAddItem=callback
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    override fun onBindViewHolder(holder: CustomerProductViewHolder, position: Int) {
        val std=stdList[position]
        holder.binding.txtpnm.text=std.productName
        holder.binding.txtpri.text=std.productPrice.toString()
        holder.binding.txtpqt.text=std.productQty.toString()
        //holder.bindView(std)

//        holder.itemView.setOnClickListener {
//            onItemClick.onShortClick(position, std)
//        }
//
//        holder.itemView.setOnLongClickListener {
//            onItemClick.onLongClick(position, std)
//
//            true
//        }

        holder.itemView.setOnClickListener { onClickItem?.invoke(std) }
        holder.binding.imageAdd.setOnClickListener { onClickAddItem?.invoke(std) }
    }
    fun setData(data:List<Product>){
        stdList.apply {
            clear()
            addAll(data)
        }
    }


    class CustomerProductViewHolder(var binding: CardItemStd1Binding): RecyclerView.ViewHolder(binding.root){

    }
}