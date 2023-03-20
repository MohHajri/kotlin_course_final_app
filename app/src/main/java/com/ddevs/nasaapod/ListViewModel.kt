package com.ddevs.nasaapod

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception


class ListViewModel: ViewModel() {
    private val _todayApod=MutableLiveData<Apod>()
    val todayApod:LiveData<Apod>
    get() = _todayApod

    private val _navigate=MutableLiveData<Apod>()
    val navigate:LiveData<Apod>
    get()=_navigate

    val status=MutableLiveData<String>()

    private val _listApod=MutableLiveData<List<Apod>>()
    val listApod:LiveData<List<Apod>>
    get()=_listApod
    init{
        getTodayApod()
        getListApod()
    }

    private fun getTodayApod(){
        viewModelScope.launch {
            try {
                _todayApod.value = ApodAPI.service.getTodayApod("vlrBxazX0c00zXNeMSpOZ8HBeQQjblm8XbQRpZsf")
            }
            catch(e:Exception){
                status.value="ERROR Loading Data"
            }
        }
    }

    private fun getListApod(){
        viewModelScope.launch {
            try {
                _listApod.value = ApodAPI.service.getList("vlrBxazX0c00zXNeMSpOZ8HBeQQjblm8XbQRpZsf")
            }
            catch(e:Exception){
                status.value="ERROR Loading Data"
            }
        }
    }

    fun navigateToDetail(selectedApod:Apod){
        _navigate.value=selectedApod
    }

    fun navigateToTodayDetail(){
        navigateToDetail(todayApod.value!!)
    }
    fun doneNavigating(){
        _navigate.value=null
    }

}
