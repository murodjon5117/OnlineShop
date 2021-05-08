package com.project.onlineshop.model

import androidx.room.Entity
import java.io.Serializable
@Entity(tableName = "products")
data class ProductModel(
    val id: Int,
    val name: String,
    val price:String,
    val image: String,
    var cartCount: Int
):Serializable