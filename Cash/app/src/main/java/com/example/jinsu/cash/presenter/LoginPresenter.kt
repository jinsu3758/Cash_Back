package com.example.jinsu.cash.presenter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.jinsu.cash.Repository
import com.example.jinsu.cash.common.Constant
import com.example.jinsu.cash.contract.LoginContract
import com.example.jinsu.cash.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter : LoginContract.Presenter {

    lateinit override var view: LoginContract.VIew

    override fun autoLogin() {
        var my_user : User?
        if(Constant.prefs.user_data != null)
        {
            Log.d("my_login","자동 로그인 성공")
            my_user = Constant.prefs.user_data
            view.start()
        }
    }

    override fun onLogin(id: String, pw : String) {
        var call : retrofit2.Call<User> = Repository.retroService!!.onLogin(id)
        call.enqueue(object : Callback<User>
        {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.e("main_repo","로그인 전송 성공 : " )
                Constant.prefs.user_data = response.body()
                view.start()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("main_repo","로그인 전송 실패 " + t.message )
            }
        })
    }

    override fun permissionCheck() {
        //접근 거절 상태일 경우
        if (ContextCompat.checkSelfPermission((view as Context),
                        Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            //사용자가 이전에 거절한 이력이 있는 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(view as Activity,
                            Manifest.permission.READ_PHONE_STATE)) {

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(view as Activity,
                        arrayOf<String>(Manifest.permission.READ_PHONE_STATE,Manifest.permission.BLUETOOTH),
                        Constant.MY_PERMISSIONS_REQUEST_READ_CONTACTS)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }//사용자가 다시 보지 않기에 체크하고 거절한 이력이 있는 경우
        }
    }
}