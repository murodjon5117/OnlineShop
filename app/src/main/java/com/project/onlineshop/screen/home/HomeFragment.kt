package com.project.onlineshop.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.project.onlineshop.R
import com.project.onlineshop.api.Api
import com.project.onlineshop.model.BaseResponse
import com.project.onlineshop.model.CatigoryModel
import com.project.onlineshop.model.OfferModel
import com.project.onlineshop.screen.MainViewModel
import com.project.onlineshop.view.CategoryAdapterCallBack
import com.project.onlineshop.view.CatigoryAdapter
import com.project.onlineshop.view.ProductAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    //var offers:List<OfferModel> = emptyList()
    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe.setOnRefreshListener{
            loadData()
        }
        recyclerCategory.layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        recyclerTopProduct.layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(),it,Toast.LENGTH_LONG).show()
        })
        viewModel.progress.observe(requireActivity(), Observer {
            swipe.isRefreshing=it
        })
        viewModel.offersData.observe(requireActivity(), Observer {
            carouselView.setImageListener { position, imageView ->
                Glide.with(imageView).load("http://osonsavdo.sd-group.uz/images/${it[position].image}").into(imageView)
            }
            carouselView.pageCount=it.count()
        })
        viewModel.categoriesData.observe(requireActivity(), Observer {
            recyclerCategory.adapter= CatigoryAdapter(it, object: CategoryAdapterCallBack{
                override fun onClickItem(item: CatigoryModel) {
                    viewModel.getProductsByCategory(item.id)
                }
            })
        })
        viewModel.productData.observe(requireActivity(), Observer {
            recyclerTopProduct.adapter= ProductAdapter(it)
        })

        loadData()
    }
    fun loadData(){
        viewModel.getOffers()
        viewModel.getCategories()
        viewModel.getTopProduct()
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}