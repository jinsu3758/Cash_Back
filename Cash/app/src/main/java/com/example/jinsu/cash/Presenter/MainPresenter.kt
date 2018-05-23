package com.example.jinsu.cash.presenter

import android.util.Log
import com.example.jinsu.cash.contract.MainContract
import com.example.jinsu.cash.data.RetroClient
import com.example.jinsu.cash.data.RetroService
import com.example.jinsu.cash.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainPresenter : MainContract.Presenter {

    private lateinit var view: MainContract.VIew
    private lateinit var retroService : RetroService
    private lateinit var retroClient : RetroClient<RetroService>
//    private var user : User? = null
    private lateinit var user : User

    override fun setView(view: MainContract.VIew) {
            this.view = view
    }

    //User데이터 수신
    override fun initUser() {
        Connect()

        var call : Call<User> = retroService.getRespos("meansoup")

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response != null)
                {
                    user = response.body()!!
                    view.setText(user)

                    Log.e("my_main","연결 성공")

                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Log.e("my_main", t.toString())
            }
        })

    }


    fun getUser() : User?
    {
        return this.user
    }

   // Retrofit을 통하여 api서버와 연결
    fun Connect() {
        retroClient = RetroClient()
        retroService = retroClient.getClient(RetroService::class.java)
    }
}