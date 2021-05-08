package com.project.onlineshop.screen.makeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.onlineshop.MapsActivity
import com.project.onlineshop.R
import com.project.onlineshop.model.AddressModel
import com.project.onlineshop.model.ProductModel
import com.project.onlineshop.utils.Costants
import kotlinx.android.synthetic.main.activity_make_order.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MakeOrderActivity : AppCompatActivity() {
    var address:AddressModel?=null
    lateinit var items:List<ProductModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)
        items=intent.getSerializableExtra(Costants.EXTRA_DATA) as List<ProductModel>

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
        tvTotalAmount.text =
            items.sumByDouble { it.cartCount * (it.price.replace(" ","").toDoubleOrNull() ?: 0.0) }.toString()
        edAdress.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }
    @Subscribe
    fun onEvent(address:AddressModel){
        this.address=address
        edAdress.setText("${address.latitude},${address.longitude}")
    }
}