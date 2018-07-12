package com.example.jinsu.cash.activity

import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.*
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.jinsu.cash.R
import com.example.jinsu.cash.common.Constant
import com.example.jinsu.cash.contract.LoginContract
import com.example.jinsu.cash.presenter.LoginPresenter
import com.example.jinsu.cash.util.BluetoothCallback
import com.example.jinsu.cash.util.BluetoothService
import com.example.jinsu.cash.util.SitService
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity(),LoginContract.VIew,BluetoothCallback {

    lateinit var presenter : LoginPresenter
    lateinit var sitIntent : Intent
    lateinit var glide : RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("my_login","onCreate")
        presenter = LoginPresenter().apply {
            view = this@LoginActivity
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        init()
    }

    override fun onStart() {
        super.onStart()

        //자동 로그인
        /*
        if(Constant.prefs.user_data != null)
        {
            presenter.autoLogin()
        }*/
    }

    fun init()
    {
        BluetoothService.get.setCallback(this)
        glide = Glide.with(this)
        glide.load(R.drawable.logo).into(login_im_logo)



        presenter.permissionCheck()
        login_btn_login.setOnClickListener()
        {
//            presenter.onLogin(login_edit_id.text.toString(), login_edit_pw.text.toString())
            startActivity(Intent(this,MainActivity::class.java))
        }
        login_btn_sign.setOnClickListener()
        {
            startActivity(Intent(this,SignInActivity::class.java))
        }

        /*login_btn_blue.setOnClickListener()
        {
            if(BluetoothService.get.deviceState)
            {
                BluetoothService.get.enableBluetooth();
            }
        }*/

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == BluetoothService.get.REQUEST_ENABLE_BT)
        {
            Log.d("login_at","블루투스 활성화")
            //블루투스 활성화 확인버튼 눌렀을 경우
            if (resultCode == Activity.RESULT_OK) {
                BluetoothService.get.scanDevice()
            } else {
//                finish()
            }//취소 버튼 눌렀을 경우

        } else if (requestCode == BluetoothService.get.REQUEST_CONNECT_DEVICE) {
                Log.d("sit_service","서비스 호출")
            sitIntent = Intent(this, SitService ::class.java)
            try {
                val mainFilter = IntentFilter("make.a.yong.sit")
                registerReceiver(receiver, mainFilter)
                startService(sitIntent)

            } catch (e: Exception) {
                // TODO: handle exception
                Toast.makeText(applicationContext, e.message,
                        Toast.LENGTH_LONG).show()

            }

            BluetoothService.get.getDeviceInfo(data)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("my_login","onResume")
    }

    override fun startIntent(status: Int) {
        Log.d("login_at","여긴가...")

        if (status == BluetoothService.get.REQUEST_ENABLE_BT) {
            Log.d("login_at","블루투스 활성화 가능하다")
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent, status)
        } else if (status == BluetoothService.get.REQUEST_CONNECT_DEVICE) {
            Log.d("login_at","여긴가??...")

            val intent = Intent(this, DeviceListActivity::class.java)
            startActivityForResult(intent, status)
        }
    }

    val receiver = object :BroadcastReceiver()
    {
        override fun onReceive(contxt: Context?, intent: Intent?) {
                Log.d("서비스 확인","context : "+contxt)
        }
    }

    override fun start() {

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    override fun setDialog(message: String, type: Int) {
        AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(resources.getString(R.string.check), DialogInterface.OnClickListener
                { dialog, which ->
                    return@OnClickListener
                }
                ).show()
    }

}