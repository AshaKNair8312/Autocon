package com.example.checkoutpointapplication.dataprovider

data class PrintResponse(
    val printType1:String?,
    val printData1:String?,
    val printAlign1:Int?,
    val printFont1:Int?,
    val printType2:String?,
    val printData2:String?,
    val printAlign2:Int?,
    val printFont2:Int?
)

data class PrintTypes(
    val PRINT_HEADER:ArrayList<PrintResponse>?,
    val PRINT_BODY:ArrayList<PrintResponse>?,
    val PRINT_FOOTER:ArrayList<PrintResponse>?
)

data class PrintData(
    var name:String="",
    var address:String="",
    var country:String="",
    var transactionNumber:String="",
    var invoiceTitle:String="",
    var date:String="",
    var cashier:String="",
    var itemInfoTitle:String="",
    var itemBrand:String="",
    var itemRate:String="",
    var itemValue:String="",
    var itemName:String="",
    var itemType:String="",
    var subTotal:String="",
    var taxableAmount:String="",
    var vat:String="",
    var netTotal:String="",
    var vatTitle:String="",
    var vatPercentage:String="",
    var thankyou:String=""

)