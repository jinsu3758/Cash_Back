package com.example.jinsu.cash.data

import com.example.jinsu.cash.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetroService {
    @GET("/users/{KEY}")
    fun getRespos(@Path("KEY") id: String): Call<User>

    /*@GET("/All_Gift")
    abstract fun getGift(): Call<ArrayList<Gift>>

    //자신이 가지고 있는 혜택
    @GET("/postCardKey")
    abstract fun getMyGift(@Query("card_key") card_key: String): Call<ArrayList<Gift>>*/

    @GET("/user")
    fun getUser(): Call<User>

   /* @GET("/findAll")
    abstract fun get(): Call<List<Test>>

    @POST("/registration")
    abstract fun post(@Body test: Test): Call<Test>*/

    /*@GET("/users/{KEY}")
    Call<User> getUser(@Path("KEY") String key, @Path("id") String id);*/

    @POST
    fun setUser(@Body user: User) : Call<String>
    
    @POST("/users/{KEY}")
    fun postRespos(@Path("KEY") id: String, @Body user: User): Call<User>

}