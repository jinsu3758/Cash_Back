package com.example.jinsu.cash.common

import com.example.jinsu.cash.MySharedPreferences

object Constant {
    val REQUEST_IMAGE : Int = 0
    var UUID : String = ""
    val CASH_ID : Int = 0

    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0

    val DIALOG_COMMON= 0
    val DIALOG_OVER_ID= 1
    val DIALOG_OVER_NOT_ID= 2
    val DIALOG_OVER_NICK= 3
    val DIALOG_OVER_NOT_NICK= 4
    val DIALOG_SIGN= 5



    lateinit var prefs : MySharedPreferences
    val BASE_URL = "http://13.209.7.229:8080/"

}