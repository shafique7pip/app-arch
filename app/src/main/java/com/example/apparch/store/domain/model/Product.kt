package com.example.apparch.store.domain.model

//we're mocking the response from our fake api in this very first class. (https://fakestoreapi.com/products)
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)
