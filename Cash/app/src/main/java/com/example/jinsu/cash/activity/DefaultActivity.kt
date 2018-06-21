package com.example.jinsu.cash.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jinsu.cash.R

class DefaultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)
        init()
    }

    fun init()
    {

    }
}