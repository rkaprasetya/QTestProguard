package com.raka.qtest.data.model

data class ProductListCompact( val productId: Int? = null,
                              val thumbnail:String? = null,
                              val productName: String? = null,
                              val price: Int? = null,
                              val stock: Int? = null)
data class BannerCompact(val banner:String?=null)