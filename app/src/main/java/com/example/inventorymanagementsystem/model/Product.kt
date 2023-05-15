package com.example.inventorymanagementsystem.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    var productName:String,
    var productPrice:Int,
    var productQty:Int,
    var shopkeeperId:Int
){
    @PrimaryKey(autoGenerate = true) var id: Int=0

}
