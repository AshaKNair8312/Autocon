package com.example.checkoutpointapplication.utils

import android.content.Context
import java.io.IOException
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber


fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        Timber.d("jsonFileException ${ioException}")
        ioException.printStackTrace()
        return null
    }
    Timber.d("jsonFileException-> $jsonString")
    return jsonString
}

