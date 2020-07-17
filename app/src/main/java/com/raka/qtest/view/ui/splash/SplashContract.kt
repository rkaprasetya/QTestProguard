package com.raka.qtest.view.ui.splash

import com.raka.qtest.data.model.ProductListResponse
import com.raka.qtest.data.model.ProductLocal
import com.raka.qtest.view.base.BasePresenter
import io.reactivex.Completable
import io.reactivex.Single

interface SplashContract {
    interface View{
        fun openProductList()
    }
    interface Presenter:BasePresenter{
        fun loadProductList()
    }
    interface Repository{
        fun getProductListRemote():Single<ProductListResponse>
        fun getProductListLocal():Single<List<ProductLocal>>
        fun insertProductListToDb(list:List<ProductLocal>):Single<Unit>
    }
}