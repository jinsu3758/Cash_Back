package com.example.jinsu.cash.contract

interface LoginContract {
    //View의 메서드를 정의
    interface VIew
    {
        fun start()
        fun setDialog(message : String, type : Int)
    }

    //Rresenter의 메서드를 정의
    interface Presenter
    {
        var view : LoginContract.VIew
        fun autoLogin()
        fun onLogin(id : String, pw : String)
        fun permissionCheck()

    }
}