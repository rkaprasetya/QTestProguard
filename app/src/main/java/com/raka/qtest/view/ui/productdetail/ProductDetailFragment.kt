package com.raka.qtest.view.ui.productdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.raka.qtest.R
import com.raka.qtest.data.database.AppDatabase
import com.raka.qtest.data.database.ParametersDao
import com.raka.qtest.data.model.ProductDetailCompact
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_product_detail.view.*

class ProductDetailFragment : Fragment(),ProductDetailContracts.View {
    private lateinit var presenter: ProductDetailContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = ProductDetailFragmentArgs.fromBundle(arguments!!).id
        val dao : ParametersDao = AppDatabase.getInstance(requireContext()).parametersDao()
        val repository:ProductDetailContracts.Repository = ProductDetailRepositoryImpl(dao)
        presenter = ProductDetailPresenterImpl(this,repository,ProductDetailMapper())
        presenter.getProductDetail(id)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_product_detail, container, false)
        val isTablet = context!!.resources.getBoolean(R.bool.isTablet)
        if(!isTablet){
            mView.appbar_product_detail.setOnClickListener {
                findNavController().popBackStack()
            }
            val appBar :AppBarLayout = mView.findViewById(R.id.appbar_product_detail)
            appBar.visibility = View.VISIBLE
        }
        return mView
    }
    override fun setData(productDetailCompact: ProductDetailCompact) {
        Glide.with(this).load(productDetailCompact.large).into(iv_detail)
        iv_detail.visibility = View.VISIBLE
        ll_detail.visibility = View.VISIBLE
        tv_detail_appbar.text = productDetailCompact.productName
        tv_detail_desc.text = productDetailCompact.description
        tv_detail_price.text = getString(R.string.detail_price,productDetailCompact.price.toString())
        tv_detail_title.text = productDetailCompact.productName
    }
    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
