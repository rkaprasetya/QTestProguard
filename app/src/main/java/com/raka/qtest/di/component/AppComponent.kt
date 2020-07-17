//package com.raka.qtest.di.component
//
//import android.app.Application
//import com.raka.qtest.App
//import com.raka.qtest.di.module.AppModule
//import com.raka.qtest.di.module.ActivityBuilder
//import com.raka.qtest.di.module.SplashFragmentModule
//import dagger.BindsInstance
//import dagger.Component
//import dagger.android.AndroidInjectionModule
//import dagger.android.AndroidInjector
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [AppModule::class,ActivityBuilder::class,AndroidInjectionModule::class])
//interface AppComponent:AndroidInjector<App> {
//    @Component.Builder
//    interface Builder{
//        @BindsInstance
//        fun application(application: Application):Builder
//        fun build():AppComponent
//    }
//    override fun inject(app:App)
//}