package com.example.checkoutpointapplication.dataprovider

data class BaseResponse<T>(
    val IsSuccess:Boolean?,
    val ErrCode:String?,
    val ErrMsg:String?,
    val ResponseData:ArrayList<T>?
)

data class HistoryResponse(
    val INVH_DT:String?,
    val INVH_TM:String?,
    val INVH_SERV_MODE:String?,
    val INVH_NET_TOTAL:Double?,
    val INVH_POYNT_STATUS:String?,
    val INVH_CUR_CODE:String?
)