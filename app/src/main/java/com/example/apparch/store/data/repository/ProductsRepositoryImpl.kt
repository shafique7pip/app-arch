package com.example.apparch.store.data.repository

import arrow.core.Either
import com.example.apparch.store.data.mapper.toNetworkError
import com.example.apparch.store.data.remote.ProductsApi
import com.example.apparch.store.domain.model.NetworkError
import com.example.apparch.store.domain.model.Product
import com.example.apparch.store.domain.repository.ProductsRepository

class ProductsRepositoryImpl constructor(
    private val productsApi: ProductsApi
) : ProductsRepository {

    override suspend fun fetchProducts(): Either<NetworkError, List<Product>> {
        //whatever the success of this call is that is mapped to right of Either.
        // Now let's create a mapper to map the left part of Either
        return Either.catch {
            productsApi.getProducts()
        }.mapLeft {
            it.toNetworkError()
        } //here via the .mapLeft {} func the left part of Either is finally mapped!
    }

}