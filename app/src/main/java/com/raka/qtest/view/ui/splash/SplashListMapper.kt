package com.raka.qtest.view.ui.splash

import com.raka.qtest.data.model.*

class SplashListMapper {
    fun convertProductListRemoteToLocal(dataRemote:List<ProductsItem?>?,banner: String):MutableList<ProductLocal>{
        val listLocal : MutableList<ProductLocal> = mutableListOf()
        dataRemote!!.forEach { item ->
            listLocal.add(ProductLocal(item!!.productId,
            item.price,item.description,
            item.stock,item.productName,item.images!!.thumbnail,
            item.images.large,banner))
        }
        return listLocal
    }
}