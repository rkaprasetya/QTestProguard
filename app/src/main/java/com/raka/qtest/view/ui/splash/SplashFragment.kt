package com.raka.qtest.view.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.raka.qtest.R
import com.raka.qtest.data.api.ApiClient
import com.raka.qtest.data.api.ApiService
import com.raka.qtest.data.database.AppDatabase
import com.raka.qtest.data.database.ParametersDao

class SplashFragment() : Fragment(),SplashContract.View {
    private lateinit var presenter: SplashContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val apiService : ApiService = ApiClient().apiService
         val dao : ParametersDao = AppDatabase.getInstance(requireContext()).parametersDao()
         val repository : SplashContract.Repository = SplashRepositoryImpl(apiService, dao)
        presenter = SplashPresenterImpl(SplashListMapper(),this,repository)
        presenter.loadProductList()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
    override fun openProductList() {
        val action = SplashFragmentDirections.actionSplashFragmentToProductListFragment()
        findNavController().navigate(action)
    }
}