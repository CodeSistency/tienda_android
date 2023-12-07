package com.plcoding.graphqlcountriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.graphqlcountriesapp.domain.Product
import com.plcoding.graphqlcountriesapp.domain.useCases.GetProductUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    val getProductsUseCase: GetProductsUseCase,
    val getProductUseCase: GetProductUseCase

): ViewModel() {

    private val _state = MutableStateFlow(ProductosState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                productos = getProductsUseCase.execute(),
                isLoading = false
            ) }
        }
    }

        data class ProductosState(
        val productos: List<Product>? = emptyList(),
        val isLoading: Boolean = false
    )
}