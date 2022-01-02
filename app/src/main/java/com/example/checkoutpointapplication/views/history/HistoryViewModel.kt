package com.example.checkoutpointapplication.views.history

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.checkoutpointapplication.dataprovider.HistoryResponse
import com.example.checkoutpointapplication.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.lifecycle.viewModelScope
import com.example.checkoutpointapplication.utils.PostDataSource
import org.json.JSONObject
import timber.log.Timber

class HistoryViewModel(@SuppressLint("StaticFieldLeak") private val appContext: Context) :
    ViewModel() {

    var showProgress = MutableLiveData<Boolean>()
    var list = ArrayList<HistoryResponse>()

    init {
        list = ArrayList()
        //showProgress.value = true
        getList()
    }

    private fun getList() {

        val jsonFileString = getJsonDataFromAsset(appContext, "history.json")
        Timber.d("jsonString $jsonFileString")
        val gson = Gson()
        if (jsonFileString != null && jsonFileString != "") {
            val jsonObject = JSONObject(jsonFileString)
            val jsonArray = jsonObject.optJSONArray("ResponseData")
            val temp = object : TypeToken<ArrayList<HistoryResponse>>() {}.type
            Timber.d("jsonString-> $temp")
            if (temp != null && jsonArray != null && jsonArray.length() != 0)
                list = gson.fromJson(jsonArray.toString(), temp)
            //  list1.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }
        }
    }

    val listData = Pager(PagingConfig(pageSize = 10)) {
        PostDataSource(appContext, list)
    }.flow.cachedIn(viewModelScope)


}