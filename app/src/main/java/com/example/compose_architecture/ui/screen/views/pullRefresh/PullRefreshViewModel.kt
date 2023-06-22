package com.example.compose_architecture.ui.screen.views.pullRefresh

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

data class Order(
    val title: String,
    val price: Double
)

data class OrderScreenState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList()
)

class PullRefreshViewModel : ViewModel() {
    private val _state = mutableStateOf(OrderScreenState())
    val state: State<OrderScreenState> = _state

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(1_000L)
            _state.value = _state.value.copy(
                isLoading = false,
                orders = _state.value.orders.toMutableList().also {
                    it.add(
                        index = 0,
                        element = Order(
                            title = "Order #${it.size + 1}",
                            price = Random.nextDouble(10.0, 100.0)
                        )
                    )
                }
            )
        }
    }
}