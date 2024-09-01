package com.example.apparch.store.pres.products_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.apparch.store.pres.products_screen.components.ProductCard
import com.example.apparch.store.pres.util.components.LoadingDialog
import com.example.apparch.store.pres.util.components.MyTopAppBar

//to make this class testable -- separate the content of the screen from itself.
//so make it internal just so you access to from current module.

@Composable //the controllers like fragments or activities will be inside following composable and the state things would be inside ProductsContent composable.
internal fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductsContent(state = state)
}

@Composable
fun ProductsContent(
    state: ProductsViewState
) {
    //show loading dialog which is going to be reused so extract it for other components as well.
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        //make reusable top bar.
        MyTopAppBar(title = "Products")
    }) {
        //finally show grid of products with lazy column
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) {
            items(state.products) { product ->
                ProductCard(product = product)
            }
        }
    }
}
