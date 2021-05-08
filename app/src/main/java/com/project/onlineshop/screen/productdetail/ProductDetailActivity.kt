package com.project.onlineshop.screen.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.project.onlineshop.R
import com.project.onlineshop.model.ProductModel
import com.project.onlineshop.utils.Costants
import com.project.onlineshop.utils.PrefUtils
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.greenrobot.eventbus.EventBus

class ProductDetailActivity : AppCompatActivity() {
    lateinit var item:ProductModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        cardviewBack.setOnClickListener {
            finish()
        }
        cardViewFavorite.setOnClickListener {
            PrefUtils.setFavorites(item)
            if (PrefUtils.checkFavorite(item)){
                EventBus.getDefault().post(1)
                imgFavorite.setImageResource(R.drawable.ic_heart)
            }else{
                imgFavorite.setImageResource(R.drawable.ic_passion)
            }
        }
        item=intent.getSerializableExtra(Costants.EXTRA_DATA) as ProductModel
        tvTitle.text=item.name
        tvProductName.text=item.name
        tvProductPrice.text=item.price

        if(PrefUtils.getCartCount(item)>0){
            btnAddToCart.visibility= View.GONE
        }
        if (PrefUtils.checkFavorite(item)){
            imgFavorite.setImageResource(R.drawable.ic_heart)
        }else{
            imgFavorite.setImageResource(R.drawable.ic_passion)
        }
        Glide.with(this).load(Costants.HOST_IMAGE+item.image).into(imgProduct)

        btnAddToCart.setOnClickListener {
            item.cartCount=1
            Log.i("CartCount",item.cartCount.toString())
            PrefUtils.setCart(item)
            Toast.makeText(this,"Product added to cart", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}