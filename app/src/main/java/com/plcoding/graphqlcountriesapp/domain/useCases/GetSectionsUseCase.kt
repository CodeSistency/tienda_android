package com.plcoding.graphqlcountriesapp.domain.useCases

import android.util.Log
import com.plcoding.graphqlcountriesapp.domain.ProductClient
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.model.sections.Sections

class GetSectionsUseCase(
    private val productClient: ProductClient
) {
    suspend fun execute(): List<Sections>? {
        Log.e("productos", productClient.getSections().toString())
        return productClient.getSections()
    }
}