package com.example.apparch.store.pres.products_screen

import com.example.apparch.store.domain.model.Product

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)
