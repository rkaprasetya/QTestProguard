package com.raka.qtest.view.ui.productlist

import android.util.Log
import com.raka.qtest.data.model.BannerCompact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProductListPresenterImpl(private val mapper:ProductListMapper,private val view:ProductListContracts.View, private val repository:ProductListContracts.Repository):ProductListContracts.Presenter {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private var job = Job()
    override fun loadDataFromDatabase() {
        launch {
            val data = repository.getProductListFromDb()
            if (!data.isNullOrEmpty() ){
                val banner = BannerCompact(data[0].banner)
                view.setData(banner,mapper.convertProductListLocalToCompact(data))
            }else{
                view.showNoData()
            }
        }
    }
    override fun onDestroy() {
        job.cancel()
    }
}