package com.raka.qtest.view.ui.productdetail

import com.raka.qtest.data.model.ProductDetailCompact
import com.raka.qtest.data.model.ProductLocal

class ProductDetailMapper {
    fun convertProductDetailLocalToCompact(dataLocal:ProductLocal): ProductDetailCompact {
        return ProductDetailCompact(
            dataLocal.productName,
            dataLocal.price,
            dataLocal.description,
            dataLocal.large
        )
    }
}