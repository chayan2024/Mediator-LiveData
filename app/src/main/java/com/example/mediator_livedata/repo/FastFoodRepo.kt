package com.example.mediator_livedata.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mediator_livedata.model.FastFood

class FastFoodRepo {
    private val _fastFoodItems: MutableLiveData<List<FastFood>> = MutableLiveData()

    fun getFastFoodItems(): LiveData<List<FastFood>> = _fastFoodItems

    suspend fun fetchFastFoodItems(): List<FastFood> {
        // Simulated data fetching for fast food items
        return listOf(FastFood("Burger"), FastFood("Pizza"), FastFood("Fries"))
    }
}