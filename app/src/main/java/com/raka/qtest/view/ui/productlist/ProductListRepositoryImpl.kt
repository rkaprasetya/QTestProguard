package com.raka.qtest.view.ui.productlist

import com.raka.qtest.data.database.ParametersDao
import com.raka.qtest.data.model.ProductLocal

class ProductListRepositoryImpl(private val dao: ParametersDao):ProductListContracts.Repository {
    override suspend fun getProductListFromDb(): List<ProductLocal>? {
        return dao.getProductList()
    }
}