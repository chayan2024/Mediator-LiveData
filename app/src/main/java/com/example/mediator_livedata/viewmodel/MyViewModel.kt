package com.example.mediator_livedata.viewmodel

// MyViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediator_livedata.model.FastFood
import com.example.mediator_livedata.model.MainCourse
import com.example.mediator_livedata.repo.FastFoodRepo
import com.example.mediator_livedata.repo.MainCourseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val fastFoodRepo = FastFoodRepo()
    private val mainCourseRepo = MainCourseRepo()

    private val _mergedData: MutableLiveData<List<Any>> = MutableLiveData()
    val mergedData: LiveData<List<Any>> = _mergedData

    fun fetchData() {
        viewModelScope.launch {
            val fastFoodDeferred = async(Dispatchers.IO) { fastFoodRepo.fetchFastFoodItems() }
            val mainCourseDeferred = async(Dispatchers.IO) { mainCourseRepo.fetchMainCourseItems() }

            val fastFoodItems = try {
                fastFoodDeferred.await()
            } catch (e: Exception) {
                emptyList<FastFood>() // Handle error scenario
            }

            val mainCourseItems = try {
                mainCourseDeferred.await()
            } catch (e: Exception) {
                emptyList<MainCourse>() // Handle error scenario
            }

            _mergedData.value = (fastFoodItems.map { it as Any } + mainCourseItems.map { it as Any })
        }
    }
}
