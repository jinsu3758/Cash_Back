package com.example.jinsu.cash.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jinsu.cash.R
import com.example.jinsu.cash.contract.MainContract
import com.example.jinsu.cash.model.User
import com.example.jinsu.cash.presenter.MainPresenter

class MainActivity : AppCompatActivity(),MainContract.VIew {
    private lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter().apply {
        }
        presenter.setView(this)
        presenter.initUser()


    }

    override fun setText(user : User)
    {

        //let은 값이 있는 경우에 다음 코드 블록을 수행하게 함
        /*user.let {
            my_txt.setText(user.login)

        }*/

    }



}
