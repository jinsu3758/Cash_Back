package com.example.jinsu.cash.activity

import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.*
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.jinsu.cash.R
import com.example.jinsu.cash.util.BluetoothCallback
import com.example.jinsu.cash.util.BluetoothService
import com.example.jinsu.cash.util.SitService
import kotlinx.android.synthetic.main.activity_default.*

class DefaultActivity : AppCompatActivity(){
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)
        init()
    }

    fun init() {

        BluetoothService.get.write("a\n".toByteArray())
        Log.d("BlueTOOTH", "값 보냄 a")
        //아두이노로 바른자세 측정신호 보냄
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                Log.d("message", "메세지 도착")

                default_btn_check.isEnabled = true
                default_btn_check.background = getDrawable(R.drawable.border_btn_sign)
                default_btn_check.setTextColor(resources.getColor(R.color.white))
            }
        }
        BluetoothService.get.setHandler(handler)
        default_btn_check.setOnClickListener()
        {
            finish()
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    override fun onResume() {
        super.onResume()
        Log.d("my_login","onResume")
    }



}