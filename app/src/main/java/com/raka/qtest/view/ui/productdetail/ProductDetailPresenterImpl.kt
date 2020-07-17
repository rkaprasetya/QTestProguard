package com.raka.qtest.view.ui.productdetail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProductDetailPresenterImpl(private val view:ProductDetailContracts.View,
                                 private var repository:ProductDetailContracts.Repository?,
                                 private val mapper:ProductDetailMapper):ProductDetailContracts.Presenter {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private var job = Job()
    override fun getProductDetail(id: Int) {
        launch {
            val detailProductData = repository!!.getDetailFromDb(id)
            if (detailProductData != null){
                view.setData(mapper.convertProductDetailLocalToCompact(detailProductData))
            }
        }
    }
    override fun onDestroy() {
        job.cancel()
        repository = null
    }
}