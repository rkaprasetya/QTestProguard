package com.raka.qtest.view.ui.productlist

import com.raka.qtest.data.model.ProductListCompact
import com.raka.qtest.data.model.ProductLocal

class ProductListMapper {
    fun convertProductListLocalToCompact(dataLocal:List<ProductLocal>):MutableList<ProductListCompact>{
        val listCompact:MutableList<ProductListCompact> = mutableListOf()
        dataLocal.forEach { item ->
            listCompact.add(
                ProductListCompact(
                    item.productId,
                    item.thumbnail,
                    item.productName,
                    item.price,
                    item.stock)
            )
        }
        return listCompact
    }
}