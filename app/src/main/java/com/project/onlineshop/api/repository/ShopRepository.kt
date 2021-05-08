package com.project.onlineshop.api.repository

import androidx.lifecycle.MutableLiveData
import com.project.onlineshop.api.NetworkManager
import com.project.onlineshop.model.BaseResponse
import com.project.onlineshop.model.CatigoryModel
import com.project.onlineshop.model.OfferModel
import com.project.onlineshop.model.ProductModel
import com.project.onlineshop.model.request.GetProductsByIdsRequst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Scheduler

class ShopRepository{
    val compositeDisposable =CompositeDisposable()
    fun getOffers(error:MutableLiveData<String>,progress:MutableLiveData<Boolean>,success:MutableLiveData<List<OfferModel>>){
        progress.value=true
        compositeDisposable.add(
            NetworkManager.getApiService().getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<BaseResponse<List<OfferModel>>>(){
                    override fun onNext(t: BaseResponse<List<OfferModel>>) {
                        progress.value=false
                        if (t.success){
                            success.value=t.data
                        }else{
                            error.value=t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value=false
                        error.value=e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )

    /* NetworkManager.getApiService().getOffers().enqueue(object :
            Callback<BaseResponse<List<OfferModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<OfferModel>>>,
                response: Response<BaseResponse<List<OfferModel>>>
            ) {
                progress.value=false
                if (response.isSuccessful&&response.body()!!.success){
                    success.value=response.body()!!.data
                }else{
                    error.value=response.body()?.message ?: response.message()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                error.value=t.localizedMessage
                progress.value=false
            }
        })*/
    }
    fun getCategories(error: MutableLiveData<String>,success:MutableLiveData<List<CatigoryModel>>){
        compositeDisposable.add(
            NetworkManager.getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<BaseResponse<List<CatigoryModel>>>(){
                    override fun onNext(t: BaseResponse<List<CatigoryModel>>) {
                        if (t.success){
                            success.value=t.data
                        }else{
                            error.value=t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value=e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }
    fun getTopProduct(error: MutableLiveData<String>,success:MutableLiveData<List<ProductModel>>){
        compositeDisposable.add(
            NetworkManager.getApiService().getTopProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<BaseResponse<List<ProductModel>>>(){
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success){
                            success.value=t.data
                        }else{
                            error.value=t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value=e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }
    fun getProductsByCategory(id:Int,error: MutableLiveData<String>,success:MutableLiveData<List<ProductModel>>){
        compositeDisposable.add(
            NetworkManager.getApiService().getCategoryProducts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<BaseResponse<List<ProductModel>>>(){
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success){
                            success.value=t.data
                        }else{
                            error.value=t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value=e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }
    fun getProductsByIds(ids:List<Int>,error: MutableLiveData<String>,progress: MutableLiveData<Boolean>,success:MutableLiveData<List<ProductModel>>){
        progress.value=true
        compositeDisposable.add(
            NetworkManager.getApiService().getProductByIds(GetProductsByIdsRequst(ids))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<BaseResponse<List<ProductModel>>>(){
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        progress.value=false
                        if (t.success){
                            success.value=t.data
                        }else{
                            error.value=t.message
                        }
                    }
                    override fun onError(e: Throwable) {
                        progress.value=false
                        error.value=e.localizedMessage
                    }
                    override fun onComplete() {

                    }

                })
        )
    }
}