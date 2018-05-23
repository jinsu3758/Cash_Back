package com.example.jinsu.cash

import android.util.Log
import com.example.jinsu.cash.data.RetroClient
import com.example.jinsu.cash.data.RetroService
import com.example.jinsu.cash.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private lateinit var retroService : RetroService
    private lateinit var retroClient : RetroClient<RetroService>

    fun Connect() {
        retroClient = RetroClient()
        retroService = retroClient.getClient(RetroService::class.java)
    }

    fun upLoadUser(user : User)
    {
        Connect()
        var call : Call<String> = retroService.setUser(user)

        call.enqueue(object : Callback<String>
        {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("main_repo","유저 데이터 전송 성공")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("main_repo","유저 데이터 전송 실패")
            }
        })


       /* call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response != null)
                {

                    Log.e("my_main","연결 성공")

                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Log.e("my_main", t.toString())
            }
        })*/
    }
}