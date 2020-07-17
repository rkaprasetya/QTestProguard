//package com.raka.qtest.di.module
//
//import com.raka.qtest.data.api.ApiService
//import com.raka.qtest.data.database.ParametersDao
//import com.raka.qtest.view.ProductListMapper
//import com.raka.qtest.view.ui.splash.SplashFragment
//import com.raka.qtest.view.ui.splash.SplashContract
//import com.raka.qtest.view.ui.splash.SplashPresenterImpl
//import com.raka.qtest.view.ui.splash.SplashRepositoryImpl
//import dagger.Module
//import dagger.Provides
//import javax.inject.Singleton
//
//@Module
//class SplashFragmentModule {
//    @Provides
//    @Singleton
//    fun provideSplashPresenter(view:SplashContract.View, repository:SplashContract.Repository, mapper:ProductListMapper):SplashContract.Presenter =SplashPresenterImpl(mapper,view,repository)
//    @Provides
//    @Singleton
//    fun provideProductListMapper()=ProductListMapper()
//    @Provides
//    @Singleton
//    fun provideSplashView():SplashContract.View = SplashFragment()
//    @Provides
//    @Singleton
//    fun provideSplashRepository(apiService: ApiService,dao: ParametersDao):SplashContract.Repository=SplashRepositoryImpl(apiService,dao)
//}