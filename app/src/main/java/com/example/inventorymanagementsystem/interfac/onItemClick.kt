package com.example.inventorymanagementsystem.interfac

import com.example.inventorymanagementsystem.model.Product

interface onItemClick {
    fun onShortClick(position: Int, product: Product)
    fun onLongClick(position: Int, product: Product)
}