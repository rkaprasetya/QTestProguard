package com.raka.qtest.view.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.phelat.navigationresult.BundleFragment
import com.raka.qtest.R
import com.raka.qtest.data.database.AppDatabase
import com.raka.qtest.data.database.ParametersDao
import com.raka.qtest.data.model.BannerCompact
import com.raka.qtest.data.model.ProductListCompact
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : BundleFragment(),ProductListContracts.View {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductListAdapter
    private lateinit var presenter:ProductListContracts.Presenter
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao : ParametersDao = AppDatabase.getInstance(requireContext()).parametersDao()
        val repository:ProductListContracts.Repository = ProductListRepositoryImpl(dao)
        presenter = ProductListPresenterImpl(ProductListMapper(),this,repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView:View
        val isTablet = context!!.resources.getBoolean(R.bool.isTablet)
        when{
            isTablet->{
                mView = inflater.inflate(R.layout.fragment_list_tablet,container,false)
                navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_detail_container) as NavHostFragment
                navHostFragment.navController.navigate(R.id.product_detail_tablet)
            }
            else -> {
                mView = inflater.inflate(R.layout.fragment_product_list, container, false)
                navHostFragment = NavHostFragment()
            }
        }
        recyclerView = mView.findViewById(R.id.rv_product)
        return mView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        presenter.loadDataFromDatabase()
    }
    private fun setupAdapter(){
        adapter = ProductListAdapter()
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(activity,layoutManager.orientation))
        recyclerView.adapter = adapter
    }
    override fun setData(bannerCompact: BannerCompact, list: List<ProductListCompact>) {
        adapter.updateData(list.toMutableList(),navHostFragment)
        Glide.with(this).load(bannerCompact.banner).into(iv_banner)
        recyclerView.visibility = View.VISIBLE
        iv_banner.visibility = View.VISIBLE
    }
    override fun showNoData() {
        tv_list_no_data.visibility = View.VISIBLE
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}