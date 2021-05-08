package com.project.onlineshop.api

import com.project.onlineshop.model.BaseResponse
import com.project.onlineshop.model.CatigoryModel
import com.project.onlineshop.model.OfferModel
import com.project.onlineshop.model.ProductModel
import com.project.onlineshop.model.request.GetProductsByIdsRequst
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("get_offers")
    fun getOffers(): Observable<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Observable<BaseResponse<List<CatigoryModel>>>

    @GET("get_top_products")
    fun getTopProduct(): Observable<BaseResponse<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id")categoryId :Int) : Observable<BaseResponse<List<ProductModel>>>

    @POST("get_products_by_ids")
    fun getProductByIds(@Body request: GetProductsByIdsRequst): Observable<BaseResponse<List<ProductModel>>>

}