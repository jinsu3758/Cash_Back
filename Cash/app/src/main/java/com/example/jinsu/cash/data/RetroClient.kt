package com.example.jinsu.cash.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
//코틀린에서는 Generic타입은 lateinit을 쓰기 위해서 Any를 사용한다
//
class RetroClient<T : Any> {
    lateinit private var service: T
    private val baseUrl = "https://api.github.com/"

    fun getClient(type: Class<out T>): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(type)
        return service
    }
}
