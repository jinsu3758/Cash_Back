package com.example.jinsu.cash.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jinsu.cash.R
import com.example.jinsu.cash.contract.SignInContract
import com.example.jinsu.cash.presenter.SignInPresenter
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(),SignInContract.VIew {

    private lateinit var prsenter : SignInPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        prsenter = SignInPresenter().apply {
            view = this@SignInActivity
            context = this@SignInActivity
        }

        //prsenter.autoLogin()

        sign_btn_check.setOnClickListener()
        {
            prsenter.setUser(sign_edit_id.text.toString(), sign_edit_pw.text.toString(),
                    sign_edit_pw_check.text.toString(), sign_edit_nickname.text.toString())
        }


    }




}
