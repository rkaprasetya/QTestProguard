//package com.raka.qtest.di.module
//
//import android.app.Application
//import android.content.Context
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.raka.qtest.data.api.ApiService
//import com.raka.qtest.data.database.AppDatabase
//import com.raka.qtest.data.database.ParametersDao
//import com.raka.qtest.utils.Constants
//import dagger.Module
//import dagger.Provides
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//@Module
//class AppModule() {
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
//        .readTimeout(10, TimeUnit.SECONDS)
//        .connectTimeout(10, TimeUnit.SECONDS)
//        .writeTimeout(10, TimeUnit.SECONDS)
//        .build()
//
//    @Provides
//    @Singleton
//    fun provideGson(): Gson = GsonBuilder()
//        .enableComplexMapKeySerialization()
//        .setPrettyPrinting()
//        .create()
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
//        .baseUrl(Constants.BASE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideAppDatabase(app: Application): ParametersDao {
//        val instance= AppDatabase.getInstance(app)
//        return instance!!.parametersDao()
//    }
//}