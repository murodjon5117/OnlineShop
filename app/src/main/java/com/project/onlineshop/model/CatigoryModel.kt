package com.project.onlineshop.model

data class CatigoryModel(
    val id:Int,
    val title: String,
    val icon:String,
    var checked: Boolean=false
)