package com.raka.qtest.view.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.phelat.navigationresult.BundleFragment
import com.raka.qtest.R
import com.raka.qtest.data.database.AppDatabase
import com.raka.qtest.data.database.ParametersDao
import com.raka.qtest.data.model.BannerCompact
import com.raka.qtest.data.model.ProductListCompact
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : BundleFragment(),ProductListContracts.View {
    private lateinit var adapter: ProductListAdapter
    private lateinit var presenter:ProductListContracts.Presenter
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
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        presenter.loadDataFromDatabase()
    }
    private fun setupAdapter(){
        adapter = ProductListAdapter()
        val layoutManager = LinearLayoutManager(activity)
        rv_product.layoutManager = layoutManager
        rv_product.addItemDecoration(DividerItemDecoration(activity,layoutManager.orientation))
        rv_product.adapter = adapter
    }
    override fun setData(bannerCompact: BannerCompact, list: List<ProductListCompact>) {
        adapter.updateData(list.toMutableList())
        Glide.with(this).load(bannerCompact.banner).into(iv_banner)
        rv_product.visibility = View.VISIBLE
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