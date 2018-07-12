package com.example.jinsu.cash.network

import com.example.jinsu.cash.model.User
import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    @GET("/controller/findId/")
    fun onLogin(@Query("id") id: String): Call<User>

    @FormUrlEncoded
    @POST("/controller/join/")
    fun postUser(@Field("id") id : String, @Field("pw") pw : String, @Field("nickname") nickname : String,
                 @Field("uuid") uuid : String, @Field("profile_img") profile_img : String, @Field("id_group") id_group : Int) : Call<User>

    @GET("/controller/checkId")
    fun checkId(@Query("id") id : String ): Call<String>

    @GET("/controller/checkNick")
    fun checkNick(@Query("nickname") nickname : String ): Call<String>

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