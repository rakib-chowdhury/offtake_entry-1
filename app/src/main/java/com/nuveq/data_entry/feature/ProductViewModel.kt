package com.nuveq.data_entry.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.model.ErrorResponse
import com.nuveq.data_entry.model.data_post.DataEntryPost
import com.nuveq.data_entry.model.history.DataHistoryResponse
import com.nuveq.data_entry.model.porduct_data.ProductResponse
import com.nuveq.data_entry.repository.ProductRepository
import javax.inject.Inject

class ProductViewModel @Inject constructor(private var repository: ProductRepository) : ViewModel() {

    fun observePost(): LiveData<DataResource<ErrorResponse>> {
        return repository.postResponse
    }

    fun postProductData(dataEntryPost: DataEntryPost) {
        repository.postProduct(dataEntryPost)
    }

    fun getProductData() {
        repository.productData()
    }


    fun getProductHistory(data:String) {
        repository.productHistory(data)
    }


    fun observeProductList(): LiveData<DataResource<ProductResponse>> {
        return repository.product
    }

    fun observeProductHistory(): LiveData<DataResource<DataHistoryResponse>> {
        return repository.history
    }
}