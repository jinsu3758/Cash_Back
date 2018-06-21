package com.example.jinsu.cash.model

import com.google.gson.annotations.SerializedName

class User
{
    @SerializedName("cash_key")
    var cash_key: Int = 0

    @SerializedName("id")
    var id: String? = null

    @SerializedName("pw")
    var pw: String? = null

    @SerializedName("nickname")
    var nickname: String? = null

    @SerializedName("uuid")
    var uuid: String? = null

    @SerializedName("point")
    var point: Int = 0

    @SerializedName("profile_img")
    var profile_img: String? = null

    @SerializedName("id_group")
    var id_group: Int = 0
}



