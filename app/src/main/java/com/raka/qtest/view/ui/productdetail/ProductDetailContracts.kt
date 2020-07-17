package com.raka.qtest.view.ui.productdetail

import com.raka.qtest.data.model.ProductDetailCompact
import com.raka.qtest.data.model.ProductLocal
import com.raka.qtest.view.base.BasePresenter

interface ProductDetailContracts{
    interface View{
        fun setData(productDetailCompact: ProductDetailCompact)
    }
    interface Presenter:BasePresenter{
        fun getProductDetail(id:Int)
    }
    interface Repository{
        suspend fun getDetailFromDb(id:Int): ProductLocal?
    }
}