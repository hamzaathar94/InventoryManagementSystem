package com.example.inventorymanagementsystem

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventorymanagementsystem.interfac.ProductDao
import com.example.inventorymanagementsystem.model.Product
import com.example.inventorymanagementsystem.model.User
import java.util.concurrent.locks.Lock

@Database(entities = [Product::class,User::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getProductdao():ProductDao

    companion object{

        @Volatile
        private var instance:AppDatabase?=null

        operator fun invoke(context: Context)= instance?: synchronized(this){
            instance?:buildDatabase(context).also {
                instance=it
            }
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()


    }
}