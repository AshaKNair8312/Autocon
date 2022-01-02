package com.example.checkoutpointapplication.views.print

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.example.checkoutpointapplication.dataprovider.HistoryResponse
import com.example.checkoutpointapplication.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.lifecycle.viewModelScope
import com.example.checkoutpointapplication.dataprovider.PrintData
import com.example.checkoutpointapplication.dataprovider.PrintTypes
import com.example.checkoutpointapplication.utils.PostDataSource
import com.google.gson.JsonObject
import org.json.JSONObject
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class PrintViewModel(@SuppressLint("StaticFieldLeak") private val appContext: Context) :
    ViewModel() {

    var showProgress = MutableLiveData<Boolean>()
    var list = ArrayList<PrintTypes>()
    var printdata = MutableLiveData<PrintData>()

    init {
        list = ArrayList()
        //showProgress.value = true
        getList()
    }

    private fun getList() {
val tempPrint=PrintData()
        val jsonFileString = getJsonDataFromAsset(appContext, "print.json")
        val gson = Gson()
        if (jsonFileString != null && jsonFileString != "") {
            val jsonObject = JSONObject(jsonFileString)
            val jsonArray = jsonObject.optJSONArray("ResponseData")
            val temp = object : TypeToken<ArrayList<PrintTypes>>() {}.type
            if (temp != null && jsonArray != null && jsonArray.length() != 0)
                list = gson.fromJson(jsonArray.toString(), temp)

            list.forEach {
                Timber.d("jsonString ${it.PRINT_HEADER?.get(0)?.printData1}")
                    tempPrint.name = it.PRINT_HEADER?.get(0)?.printData1?.trim().toString()
                    tempPrint.address = it.PRINT_HEADER?.get(1)?.printData1?.trim().toString()
                    tempPrint.country = it.PRINT_HEADER?.get(2)?.printData1?.trim().toString()
                    tempPrint.transactionNumber = it.PRINT_HEADER?.get(4)?.printData1.toString().trim()
                    tempPrint.invoiceTitle = it.PRINT_HEADER?.get(6)?.printData1.toString()
                    tempPrint.date = it.PRINT_HEADER?.get(8)?.printData1?.substringAfter(":").toString().trim()
                    tempPrint.cashier = it.PRINT_HEADER?.get(9)?.printData1?.substringAfter(":").toString().trim()
                    tempPrint.itemBrand = it.PRINT_BODY?.get(3)?.printData1?.substring(0..9).toString()
                    tempPrint.itemRate = it.PRINT_BODY?.get(3)?.printData1?.substring(12..17).toString().trim()
                    tempPrint.itemValue = it.PRINT_BODY?.get(3)?.printData1?.substring(19..25).toString().trim()
                    tempPrint.itemName = it.PRINT_BODY?.get(4)?.printData1?.trim().toString()
                    tempPrint.itemType = it.PRINT_BODY?.get(5)?.printData1?.trim().toString()
                        .lowercase(Locale.getDefault())
                        .replaceFirstChar { it1->if (it1.isLowerCase()) it1.titlecase(Locale.getDefault()) else it1.toString() }
                    tempPrint.subTotal = it.PRINT_FOOTER?.get(1)?.printData1.toString()
                    tempPrint.taxableAmount = it.PRINT_FOOTER?.get(2)?.printData1.toString()
                    tempPrint.vat = it.PRINT_FOOTER?.get(3)?.printData1.toString()
                    tempPrint.netTotal = it.PRINT_FOOTER?.get(5)?.printData1.toString()
                    tempPrint.vatTitle = it.PRINT_FOOTER?.get(7)?.printData1.toString()
                    tempPrint.vatPercentage = it.PRINT_FOOTER?.get(8)?.printData1.toString()
                    tempPrint.thankyou = it.PRINT_FOOTER?.get(11)?.printData1.toString()

            }
            Timber.d("printData $tempPrint")
            printdata.value=tempPrint

        }
    }



}