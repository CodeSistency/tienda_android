package com.plcoding.graphqlcountriesapp.presentation

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.graphqlcountriesapp.domain.model.categories.Categories
import com.plcoding.graphqlcountriesapp.domain.model.products.Product
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductDetail
import com.plcoding.graphqlcountriesapp.domain.model.sections.Sections
import com.plcoding.graphqlcountriesapp.domain.model.slider.Slider
import com.plcoding.graphqlcountriesapp.domain.useCases.GetCategoriesUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetProductUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetProductsByCategoriesUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetProductsUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetSectionsUseCase
import com.plcoding.graphqlcountriesapp.domain.useCases.GetSliderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    val getProductsUseCase: GetProductsUseCase,
    val getProductUseCase: GetProductUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getSectionsUseCase: GetSectionsUseCase,
    val getSliderUseCase: GetSliderUseCase,
    val getProductsByCategoriesUseCase: GetProductsByCategoriesUseCase,
): ViewModel() {

    private val _stateProducts = MutableStateFlow(ProductosState())
    val stateProducts = _stateProducts.asStateFlow()

    private val _stateProduct = MutableStateFlow(ProductoState())
    val stateProduct = _stateProduct.asStateFlow()

    private val _stateSliders = MutableStateFlow(SlidersState())
    val stateSliders = _stateSliders.asStateFlow()

    private val _stateSections = MutableStateFlow(SectionsState())
    val stateSections = _stateSections.asStateFlow()

    private val _stateCategories = MutableStateFlow(CategoriesState())
    val stateCategories = _stateCategories.asStateFlow()

    private val _stateProductsByCategory = MutableStateFlow(ProductosState())
    val stateProductsByCategory = _stateProducts.asStateFlow()

    init {
        viewModelScope.launch {
            _stateProducts.update { it.copy(
                isLoading = true
            ) }
            _stateProducts.update { it.copy(
                productos = getProductsUseCase.execute(),
                isLoading = false
            ) }
//            _stateProduct.update { it.copy(
//                isLoading = true
//            ) }
//            _stateProduct.update { it.copy(
//                producto = getProductUseCase.execute("10"),
//                isLoading = false
//            ) }
            _stateCategories.update { it.copy(
                isLoading = true
            ) }
            _stateCategories.update { it.copy(
                categorias = getCategoriesUseCase.execute(),
                isLoading = false
            ) }
            _stateSliders.update { it.copy(
                isLoading = true
            ) }
            _stateSliders.update { it.copy(
                sliders = getSliderUseCase.execute(),
                isLoading = false
            ) }
            _stateSections.update { it.copy(
                isLoading = true
            ) }
            _stateSections.update { it.copy(
                sections = getSectionsUseCase.execute(),
                isLoading = false
            ) }

        }
    }

    suspend fun getProductDetail(id: String){
        _stateProduct.update { it.copy(
            isLoading = true
        ) }
        _stateProduct.update { it.copy(
            producto = getProductUseCase.execute(id),
            isLoading = false
        ) }
    }

    suspend fun getProductsByCategory(category: String){
        _stateProductsByCategory.update { it.copy(
            isLoading = true
        ) }
        _stateProductsByCategory.update { it.copy(
            productos = getProductsByCategoriesUseCase.execute(category),
            isLoading = false
        ) }
    }
        data class ProductosState(
            val productos: List<Product>? = emptyList(),
            val isLoading: Boolean = false
        )
        data class ProductoState(
            val producto: ProductDetail? = null,
            val isLoading: Boolean = false
        )
        data class SlidersState(
            val sliders: List<Slider>? = emptyList(),
            val isLoading: Boolean = false
        )
        data class CategoriesState(
            val categorias: List<Categories>? = emptyList(),
            val isLoading: Boolean = false
        )
        data class SectionsState(
            val sections: List<Sections>? = emptyList(),
            val isLoading: Boolean = false
        )
        data class ProductosByCategoryState(
            val productos: List<Product>? = emptyList(),
            val isLoading: Boolean = false
        )

    fun sendMessageToWhatsApp(context: Context, number: String, message: String) {
        val url =
            "https://api.whatsapp.com/send?phone=$number&text=${Uri.encode(message)}"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            this.data = Uri.parse(url)
            this.`package` = "com.whatsapp"
        }

        try {
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            //whatsapp not installled
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data = Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
            context.startActivity(playStoreIntent)
        }
    }
}