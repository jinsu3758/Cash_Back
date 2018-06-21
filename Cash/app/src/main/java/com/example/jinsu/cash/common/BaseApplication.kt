package com.example.jinsu.cash.common

import android.app.Application
import com.example.jinsu.cash.MySharedPreferences


class BaseApplication : Application() {



    override fun onCreate() {
        super.onCreate()
        Constant.prefs = MySharedPreferences(this)

    }


}