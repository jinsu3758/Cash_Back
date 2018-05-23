package com.example.jinsu.cash.presenter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import com.example.jinsu.cash.BaseApplication
import com.example.jinsu.cash.R
import com.example.jinsu.cash.Repository
import com.example.jinsu.cash.contract.SignInContract
import com.example.jinsu.cash.model.User
import com.example.jinsu.cash.view.MainActivity

class SignInPresenter : SignInContract.Presenter
{
    lateinit override var view : SignInContract.VIew
    lateinit override var context: Context

    private lateinit var user : User
    private var repository: Repository = Repository()

    /*override fun setView(view: SignInContract.VIew) {
        this.view = view
    }*/

    /**
     * User데이터 업로드
     */
    override fun setUser(id : String, pw : String, pw_check : String, nickname : String)
    {
        if(pw.equals(pw_check))
        {

            user = User("0",id,pw,nickname,"E",0,"l0",0)
           // repository.upLoadUser(user)
            BaseApplication.prefs.user_data = user

            AlertDialog.Builder(context)
                    .setMessage("회원가입을 축하드립니다!")
                    .setCancelable(false)
                    .setPositiveButton(context.resources.getString(R.string.check), DialogInterface.OnClickListener
                    { dialog, which ->
                        val intent = Intent(context,MainActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                    }
                    ).show()


        }
        //비밀번호 확인이 틀린 경우
        else
        {
            AlertDialog.Builder(context)
                    .setMessage("비밀번호 확인과 비밀번호가 틀립니다.")
                    .setCancelable(false)
                    .setNegativeButton(context.resources.getString(R.string.check), DialogInterface.OnClickListener
                    { dialog, which ->
                    }
                    ).show()
        }

    }

    override fun autoLogin() {

        var my_user : User?
        if(BaseApplication.prefs.user_data != null)
        {
            my_user = BaseApplication.prefs.user_data
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}