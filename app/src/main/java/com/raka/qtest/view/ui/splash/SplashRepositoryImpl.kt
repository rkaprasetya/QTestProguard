package com.raka.qtest.view.ui.splash

import com.raka.qtest.data.api.ApiService
import com.raka.qtest.data.database.ParametersDao
import com.raka.qtest.data.model.ProductLocal
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashRepositoryImpl (private val apiService: ApiService,private val dao:ParametersDao):SplashContract.Repository {
    override fun getProductListRemote()  = apiService.getProductList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
    override fun getProductListLocal(): Single<List<ProductLocal>> {
        return dao.getProductListRx().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    override fun insertProductListToDb(list: List<ProductLocal>):Single<Unit> {
        return Single.fromCallable{dao.insertProductListAll(list)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}