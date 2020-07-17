package com.raka.qtest.view.ui.productlist

import com.raka.qtest.data.model.BannerCompact
import com.raka.qtest.data.model.ProductListCompact
import com.raka.qtest.data.model.ProductLocal
import com.raka.qtest.view.base.BasePresenter

interface ProductListContracts {
    interface View{
        fun setData(bannerCompact: BannerCompact,list:List<ProductListCompact>)
        fun showNoData()
    }
    interface Presenter:BasePresenter{
        fun loadDataFromDatabase()
    }
    interface Repository{
        suspend fun getProductListFromDb():List<ProductLocal>?
    }
}