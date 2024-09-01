package com.example.apparch.store.domain.repository

import arrow.core.Either
import com.example.apparch.store.domain.model.NetworkError
import com.example.apparch.store.domain.model.Product

//Either object from Arrow: left(NetworkError) = error and right = success(list of products)
interface ProductsRepository {

    suspend fun fetchProducts(): Either<NetworkError, List<Product>>
}