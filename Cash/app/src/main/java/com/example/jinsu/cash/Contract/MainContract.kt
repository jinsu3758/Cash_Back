package com.example.jinsu.cash.contract

import com.example.jinsu.cash.model.User


//View와 Presenter를 정의하기 위함
public interface MainContract {

    //View의 메서드를 정의
    interface VIew
    {
        fun setText(user : User)
    }

    //Rresenter의 메서드를 정의
    interface Presenter
    {

        fun initUser()
        fun setView(view: VIew)
    }
}