package com.project.onlineshop.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.onlineshop.R
import com.project.onlineshop.model.AddressModel
import com.project.onlineshop.screen.MainViewModel
import com.project.onlineshop.utils.PrefUtils
import com.project.onlineshop.view.ProductAdapter
import kotlinx.android.synthetic.main.activity_make_order.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class FavoriteFragment : Fragment() {
    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.productData.observe(this, Observer {
            recyclerProduct.adapter=ProductAdapter(it)
        })

        viewModel.progress.observe(this, Observer {
            swipe.isRefreshing=it
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerProduct.layoutManager=LinearLayoutManager(requireActivity())
        swipe.setOnRefreshListener {
            loadData()
        }
        loadData()
    }
    fun loadData(){
        viewModel.getProductsByIds(PrefUtils.getFavoriteList())
    }




    companion object {
        @JvmStatic
        fun newInstance() =FavoriteFragment()
    }
}