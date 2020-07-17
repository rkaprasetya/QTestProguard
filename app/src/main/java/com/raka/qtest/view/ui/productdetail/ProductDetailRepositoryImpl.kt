package com.raka.qtest.view.ui.productdetail

import com.raka.qtest.data.database.ParametersDao
import com.raka.qtest.data.model.ProductLocal

class ProductDetailRepositoryImpl(private val dao:ParametersDao):ProductDetailContracts.Repository {
    override suspend fun getDetailFromDb(id:Int): ProductLocal? {
        return dao.getProductDetail(id)
    }
}