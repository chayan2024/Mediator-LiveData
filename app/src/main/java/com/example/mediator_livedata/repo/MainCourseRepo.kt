package com.example.mediator_livedata.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mediator_livedata.model.MainCourse

class MainCourseRepo {
    private val _mainCourseItems: MutableLiveData<List<MainCourse>> = MutableLiveData()

    fun getMainCourseItems(): LiveData<List<MainCourse>> = _mainCourseItems

    suspend fun fetchMainCourseItems(): List<MainCourse> {
        // Simulated data fetching for main course items
        return listOf(MainCourse("Steak"), MainCourse("Pasta"), MainCourse("Salad"))
    }
}