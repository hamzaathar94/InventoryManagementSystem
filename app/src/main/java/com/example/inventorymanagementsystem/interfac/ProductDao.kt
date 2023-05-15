package com.example.inventorymanagementsystem.interfac

import androidx.room.*
import com.example.inventorymanagementsystem.model.Product
import com.example.inventorymanagementsystem.model.User

@Dao
interface ProductDao {
    @Insert
    suspend fun addProduct(product: Product)

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM product ORDER BY id ASC")
    suspend fun getAllProduct():List<Product>

    @Query("SELECT * FROM user ORDER BY id ASC")
    suspend fun getAllUser():List<User>

    @Query("SELECT * FROM user where UserType like :usertype limit 10 ")
    fun getOneData(usertype : String) : User

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)
}