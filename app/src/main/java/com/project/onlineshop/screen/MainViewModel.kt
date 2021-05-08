package com.project.onlineshop.screen

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.project.onlineshop.api.Api
import com.project.onlineshop.api.NetworkManager
import com.project.onlineshop.api.repository.ShopRepository
import com.project.onlineshop.model.BaseResponse
import com.project.onlineshop.model.CatigoryModel
import com.project.onlineshop.model.OfferModel
import com.project.onlineshop.model.ProductModel
import com.project.onlineshop.utils.Costants
import com.project.onlineshop.view.CatigoryAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {
    val repository = ShopRepository()
    val error=MutableLiveData<String>()
    val progress=MutableLiveData<Boolean>()


    val offersData= MutableLiveData<List<OfferModel>>()
    val categoriesData= MutableLiveData<List<CatigoryModel>>()
    val productData= MutableLiveData<List<ProductModel>>()

    fun getOffers(){
        repository.getOffers(error,progress,offersData)
    }
    fun getCategories(){
        repository.getCategories(error,categoriesData)
    }
    fun getTopProduct(){
        repository.getTopProduct(error,productData)
    }
    fun getProductsByCategory(id:Int){
        repository.getProductsByCategory(id,error,productData)
    }
    fun getProductsByIds(ids:List<Int>){
        repository.getProductsByIds(ids,error,progress,productData)
    }

}