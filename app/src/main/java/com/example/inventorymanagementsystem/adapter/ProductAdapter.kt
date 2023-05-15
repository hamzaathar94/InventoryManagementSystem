package com.example.inventorymanagementsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorymanagementsystem.databinding.CardItemStdBinding
import com.example.inventorymanagementsystem.model.Product

class ProductAdapter:RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var stdList:ArrayList<Product> = ArrayList()
    private var onClickItem:((Product)-> Unit)?=null
    private var onClickDeleteItem:((Product)-> Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view=CardItemStdBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(view)
    }

    fun setOnClickItem(callback:(Product)->Unit){
        this.onClickItem=callback
    }
    fun setOnClickDeleteItem(callback: (Product) -> Unit){
        this.onClickDeleteItem=callback
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val std=stdList[position]
        holder.binding.txtpname.text=std.productName
        holder.binding.txtprice.text=std.productPrice.toString()
        holder.binding.txtqty.text=std.productQty.toString()
        //holder.bindView(std)

        holder.itemView.setOnClickListener { onClickItem?.invoke(std) }
        holder.binding.btndelete.setOnClickListener { onClickDeleteItem?.invoke(std) }
    }
   fun setData(data:List<Product>){
       stdList.apply {
           clear()
           addAll(data)
       }

   }


    class ProductViewHolder(var binding:CardItemStdBinding): RecyclerView.ViewHolder(binding.root){

    }
}