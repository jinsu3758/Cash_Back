package com.example.jinsu.cash

import android.app.Application

class BaseApplication : Application() {
    companion object {
        lateinit var prefs : MySharedPreferences
    }

    override fun onCreate() {
        prefs = MySharedPreferences(this)
        super.onCreate()
    }
}