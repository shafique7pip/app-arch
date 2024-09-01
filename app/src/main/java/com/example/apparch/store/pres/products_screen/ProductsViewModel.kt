package com.example.apparch.store.pres.products_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apparch.store.domain.repository.ProductsRepository
import com.example.apparch.store.pres.util.components.sendEvent
import com.example.apparch.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            productsRepository.fetchProducts().onRight { product ->
                _state.update {
                    it.copy(
                        products = product,
                    )
                }
            }.onLeft { error ->
                _state.update {
                    it.copy(
                        error = error.error.message,
                    )
                }
                sendEvent(Event.Toast(error.error.message))
            }
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

}