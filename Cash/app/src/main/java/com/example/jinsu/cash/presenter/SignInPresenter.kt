package com.example.jinsu.cash.presenter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import com.example.jinsu.cash.R
import com.example.jinsu.cash.Repository
import com.example.jinsu.cash.common.Constant
import com.example.jinsu.cash.contract.SignInContract
import com.example.jinsu.cash.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SignInPresenter : SignInContract.Presenter
{

    lateinit override var view : SignInContract.VIew
    private lateinit var user : User

    /*override fun setView(view: SignInContract.VIew) {
        this.view = view
    }*/

    /**
     * User데이터 업로드
     */
    override fun setUser(id : String, pw : String, pw_check : String, nickname : String, progile_img : String,
                         agree : Boolean)
    {


        if(!agree)
        {
            view.setDialog("약관에 동의해주세요.",Constant.DIALOG_COMMON)
            view.clearEditText()
            return
        }

        if(pw.equals(pw_check))
        {
            /*if(!id.matches("^[a-zA-Z0-9]{3,15}$".toRegex()))
            {
                view.setDialog("아이디 형식이 틀립니다.\n3~15글자의 영어 및 숫자만 가능합니다.",Constant.DIALOG_COMMON)
                view.clearEditText()
                return
            }
            if(pw.length < 6)
            {
                view.setDialog("비밀번호 형식이 틀립니다.\n6자 이상만 가능합니다.",Constant.DIALOG_COMMON)
                view.clearEditText()
                return
            }

            if(nickname.length < 1)
            {
                view.clearEditText()
                return
            }
            */
            getUUID()
            if(Constant.UUID == null)
            {
                view.setDialog("다시 시도해주세요.",Constant.DIALOG_COMMON)
                view.clearEditText()
                return
            }
//            Repository.setUser(id, pw, nickname, Constant.UUID, progile_img, Constant.CASH_ID )

            var call : Call<User> = Repository.retroService!!.postUser(id,pw,nickname,Constant.UUID,progile_img,Constant.CASH_ID)

            call.enqueue(object : Callback<User>
            {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.e("main_repo","유저 데이터 전송 성공 : " )

                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("main_repo","유저 데이터 전송 실패 " + t.message )
                }
            })
            view.setDialog("회원가입을 축하드립니다!",Constant.DIALOG_SIGN)
            /*AlertDialog.Builder(view as Context)
                    .setMessage("회원가입을 축하드립니다!")
                    .setCancelable(false)
                    .setPositiveButton((view as Context).resources.getString(R.string.check), DialogInterface.OnClickListener
                    { dialog, which ->
                        view.goLogin()
                        return@OnClickListener
                    }
                    ).show()*/

        }
        //비밀번호 확인이 틀린 경우
        else
        {
            AlertDialog.Builder(view as Context)
                    .setMessage("비밀번호 확인과 비밀번호가 틀립니다.")
                    .setCancelable(false)
                    .setNegativeButton((view as Context).resources.getString(R.string.check), DialogInterface.OnClickListener
                    { dialog, which ->
                        return@OnClickListener
                    }
                    ).show()
            return
        }

    }


    fun getUUID()
    {
        val tm = (view as Context).getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val tmDevice: String
        val tmSerial: String
        val androidID: String

        tmDevice = "" + tm.deviceId
        tmSerial = "" + tm.simSerialNumber
        androidID = "" + android.provider.Settings.Secure.getString((view as Context).getContentResolver(), Settings.Secure.ANDROID_ID)
        val deviceUUid = UUID(androidID.hashCode().toLong(), tmDevice.hashCode().toLong() shl 32 or tmSerial.hashCode().toLong())

        Constant.UUID = deviceUUid.toString()
    }

    override fun checkId(id: String) {
        if(!id.matches("^[a-zA-Z0-9]{3,15}$".toRegex()))
        {
            view.setDialog("아이디 형식이 틀립니다.\n3~15글자의 영어 및 숫자만 가능합니다.",Constant.DIALOG_COMMON)
            view.clearEditText()
            return
        }
        var call : Call<String> = Repository.retroService!!.checkId(id)
        call.enqueue(object : Callback<String>
        {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("main_repo","아이디 체크  성공 : " + response.body() )
                val bool = response.body().toString()
                if(bool!!.equals("true"))
                {
                    view.setDialog("사용 가능합니다.",Constant.DIALOG_OVER_NOT_ID)

                }
                else
                {
                    view.setDialog("중복된 아이디입니다",Constant.DIALOG_OVER_ID)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("main_repo","아이디 체크 실패 " + t.message )
            }
        })
    }

    override fun checkNick(nickname: String) {
        if(nickname.length < 1)
        {
            view.setDialog("닉네임을 적어주세요.",Constant.DIALOG_COMMON)
            return
        }
        var call : Call<String> = Repository.retroService!!.checkNick(nickname)
        call.enqueue(object : Callback<String>
        {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("main_repo","닉네임 체크  성공 : " )
                val bool = response.body().toString()
                if(bool!!.equals("true"))
                {
                    view.setDialog("사용 가능합니다.",Constant.DIALOG_OVER_NOT_NICK)
                }
                else
                {
                    view.setDialog("중복된 닉네임입니다",Constant.DIALOG_OVER_NICK)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("main_repo","닉네임 체크 실패 " + t.message )
            }
        })
    }





}