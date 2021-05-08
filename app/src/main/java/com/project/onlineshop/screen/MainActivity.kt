package com.project.onlineshop.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.project.onlineshop.R
import com.project.onlineshop.screen.cart.CartFragment
import com.project.onlineshop.screen.favorite.FavoriteFragment
import com.project.onlineshop.screen.home.HomeFragment
import com.project.onlineshop.screen.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val homeFragment=HomeFragment.newInstance()
    val favoriteFragment=FavoriteFragment.newInstance()
    val cartFragment=CartFragment.newInstance()
    val profileFragment=ProfileFragment.newInstance()
    var activeFragment: Fragment =homeFragment
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.flConteiner,homeFragment,homeFragment.tag).hide(homeFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flConteiner,favoriteFragment,favoriteFragment.tag).hide(favoriteFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flConteiner,cartFragment,cartFragment.tag).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flConteiner,profileFragment,profileFragment.tag).hide(profileFragment).commit()

        supportFragmentManager.beginTransaction().show(activeFragment).commit()

        bottomNavigatiobView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                    activeFragment=homeFragment
                }
                R.id.actionCart -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(cartFragment).commit()
                    activeFragment=cartFragment
                }
                R.id.actionFavorite -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(favoriteFragment).commit()
                    activeFragment=favoriteFragment
                }
                R.id.actionProfile -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
                    activeFragment=profileFragment
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}