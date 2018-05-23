package com.example.jinsu.cash.contract

import android.content.Context

interface SignInContract {
    //View의 메서드를 정의
    interface VIew
    {

    }

    //Rresenter의 메서드를 정의
    interface Presenter
    {
        var view : SignInContract.VIew
        var context : Context
        fun setUser(id : String, pw : String, pw_check : String, nickname : String)
        fun autoLogin()
    }
}