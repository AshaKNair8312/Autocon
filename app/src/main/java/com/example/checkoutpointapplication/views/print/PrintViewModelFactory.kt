package com.example.checkoutpointapplication.views.print

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PrintViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrintViewModel::class.java)) {
            return PrintViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}