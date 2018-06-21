package com.example.jinsu.cash

import android.util.Log
import com.example.jinsu.cash.common.Constant
import com.example.jinsu.cash.model.User
import com.example.jinsu.cash.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    var retroService: RetroService? = null
    get() {getClient()
        return field}


    interface NetworkCallback
    {
        fun setUser()
    }

   /* fun getBASE_URL(): String {
        return BASE_URL
    }*/

    fun getClient() {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retroService = retrofit.create(RetroService::class.java)
    }

    fun setUser(id : String,  pw : String, nickname : String,
                 uuid : String,  profile_img : String,  id_group : Int)
    {
        getClient()
        var call : Call<User> = retroService!!.postUser(id,pw,nickname,uuid,profile_img,id_group)

        call.enqueue(object : Callback<User>
        {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.e("main_repo","유저 데이터 전송 성공 : " )

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("main_repo","유저 데이터 전송 실패 " + t.message )
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