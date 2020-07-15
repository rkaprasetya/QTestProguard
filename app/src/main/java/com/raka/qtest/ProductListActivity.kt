package com.raka.qtest

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class ProductListActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        // Enables Always-on
        setAmbientEnabled()
    }
}