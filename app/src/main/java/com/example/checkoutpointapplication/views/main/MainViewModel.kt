package com.example.checkoutpointapplication.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var clicked=MutableLiveData<Int>()

    init{
        clicked.value=0
    }

    fun onClicked(i:Int){
        clicked.value=i
    }

    fun getClicked(): LiveData<Int>{
        return clicked
    }
}