package com.example.inventorymanagementsystem.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var name: String,
    var  email: String,
    var password:String,
    var usertype:String
){
    @PrimaryKey(autoGenerate = true) var id: Int=0
}
