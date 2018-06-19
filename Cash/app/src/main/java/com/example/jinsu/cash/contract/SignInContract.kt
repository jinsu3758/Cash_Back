package com.example.jinsu.cash.contract

interface SignInContract {
    //View의 메서드를 정의
    interface VIew
    {
        fun clearEditText()
        fun goLogin()
        fun setDialog(message : String, type : Int)
    }

    //Rresenter의 메서드를 정의
    interface Presenter
    {
        var view : SignInContract.VIew
        fun setUser(id : String, pw : String, pw_check : String, nickname : String, progile_img : String,
                    agree : Boolean)
        fun checkId(id : String)
        fun checkNick(nickname : String)

    }
}