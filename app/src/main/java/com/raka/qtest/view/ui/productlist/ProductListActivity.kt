package com.raka.qtest.view.ui.productlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.raka.qtest.R

class ProductListActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        navController = Navigation.findNavController(this,R.id.main_nav_fragment)
    }
    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()
}