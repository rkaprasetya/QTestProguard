package com.raka.qtest.view.ui.splash

import android.util.Log
import com.raka.myapplication.view.utils.Util
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SplashPresenterImpl (private val mapper: SplashListMapper, private val view:SplashContract.View, private var repository: SplashContract.Repository?):SplashContract.Presenter {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private var job = Job()
    private val compositeDisposable=CompositeDisposable()
    private var isInternetAvailable = false
    override fun loadProductList() {
        launch {
            isInternetAvailable = withContext(Dispatchers.Default){
                Util.isInternetAvailable()
            }
            if (isInternetAvailable){
                loadProductListRemote()
            }else{
                view.openProductList()
            }
        }
    }
    private fun loadProductListRemote(){
        val disposable = repository?.getProductListRemote()!!
            .map {
                mapper.convertProductListRemoteToLocal(it.data?.products,it.data?.banner!!) }
            .flatMap{repository?.insertProductListToDb(it)}
            .subscribe({
               view.openProductList()
            },{
                Log.e("SplashPresenterImpl","Exception message: ${it.message}")
                view.openProductList()
            })
        compositeDisposable.add(disposable)
    }
    override fun onDestroy() {
        repository = null
        compositeDisposable.clear()
        job.cancel()
    }
}