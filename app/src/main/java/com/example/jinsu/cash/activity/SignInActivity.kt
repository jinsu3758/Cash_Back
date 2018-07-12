package com.example.jinsu.cash.activity

import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.*
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.ScrollingMovementMethod
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.jinsu.cash.R
import com.example.jinsu.cash.common.Constant
import com.example.jinsu.cash.contract.SignInContract
import com.example.jinsu.cash.presenter.SignInPresenter
import com.example.jinsu.cash.util.BluetoothCallback
import com.example.jinsu.cash.util.BluetoothService
import com.example.jinsu.cash.util.SitService
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.io.ByteArrayOutputStream


class SignInActivity : AppCompatActivity(),BluetoothCallback, SignInContract.VIew {



    private lateinit var presenter : SignInPresenter
    private lateinit var glideModule: RequestManager
    private  var profile_bitmap: Bitmap? = null
    private lateinit var handler: Handler
    private lateinit var base64 : String
    private  var idCheck : Boolean = false
    private  var nickCheck : Boolean = false
    lateinit var sitIntent : Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        handler = Handler()
        {
            true
        }

        presenter = SignInPresenter().apply {
            view = this@SignInActivity
        }
        initListener()
    }

    override fun clearEditText() {
        sign_edit_id.setText("")
        sign_edit_pw.setText("")
        sign_edit_pw_check.setText("")
        sign_edit_nickname.setText("")
        idCheck =false
        nickCheck = false
    }


    fun initListener()
    {
        glideModule = Glide.with(this)
        BluetoothService.get.setCallback(this)
        //회원가입 버튼
        sign_btn_complete.setOnClickListener()
        {
            val baos = ByteArrayOutputStream()

            if(profile_bitmap == null)
            {
                AlertDialog.Builder(this)
                        .setMessage("프로필 이미지를 넣어주세요.")
                        .setCancelable(false)
                        .setNegativeButton(resources.getString(R.string.check), DialogInterface.OnClickListener
                        { dialog, which ->
                            return@OnClickListener
                        }
                        ).show()
                clearEditText()
                return@setOnClickListener
            }

            if(!idCheck)
            {
                setDialog("아이디 중복확인이 안됐습니다.",Constant.DIALOG_COMMON)
                clearEditText()
                return@setOnClickListener
            }

            if(!nickCheck)
            {
                setDialog("닉네임 중복확인이 안됐습니다.",Constant.DIALOG_COMMON)
                clearEditText()
                return@setOnClickListener
            }

            sign_prg.visibility = View.VISIBLE
            val thread1 = Thread(Runnable {
                try {
                    sign_prg.progress = 70

                    profile_bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, baos)

                    val bImage = baos.toByteArray()
                    base64 = Base64.encodeToString(bImage, 0)

                    handler.post(Runnable {

                        onStop()
                        presenter.setUser(sign_edit_id.text.toString(), sign_edit_pw.text.toString(),
                                sign_edit_pw_check.text.toString(), sign_edit_nickname.text.toString(),base64, sign_rd_agree.isSelected)
                        sign_prg.progress = 0
                        sign_prg.visibility = View.INVISIBLE
                    })
                } catch (ex: Exception) {
                    Log.e("SampleThreadActivity", "Exception in processing message.", ex)
                }
            })
            thread1.start()

        }
        sign_txt_agree.setMovementMethod(ScrollingMovementMethod.getInstance());
        /*sign_layout.setOnTouchListener(OnTouchListener { v, event ->
            sign_txt_agree.parent.requestDisallowInterceptTouchEvent(false)
            false
        })
        sign_txt_agree.setOnTouchListener(OnTouchListener { v, event ->
            // TODO Auto-generated method stub
            sign_txt_agree.parent.requestDisallowInterceptTouchEvent(true)
            //스크롤뷰가 텍스트뷰의 터치이벤트를 가져가지 못하게 함
            false
        })*/
        sign_im_profile.setOnClickListener()
        {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, Constant.REQUEST_IMAGE)
        }

        sign_btn_default.setOnClickListener()
        {
            if (BluetoothService.get.deviceState) {
                Log.d("sign_in","OnclickListener")
                BluetoothService.get.enableBluetooth()
            }//자동로그인 시 블루투스 잡기
//            startActivity(Intent(this,DefaultActivity::class.java))
        }

        //아이디 중복확인 버튼
        sign_btn_overlapId.setOnClickListener()
        {
            presenter.checkId(sign_edit_id.text.toString())
        }
        //닉네임 중복확인 버튼
        sign_btn_overlapNick.setOnClickListener()
        {
            presenter.checkNick(sign_edit_nickname.text.toString())
        }

        sign_ch_pw.setOnClickListener {
            if(sign_ch_pw.isChecked)
            {
//                sign_edit_pw.setInputType(EditorInfo.TYPE_CLASS_TEXT)

                sign_edit_pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
            }
            else
            {
                Log.d("sign_activity","ch 해제")
                sign_edit_pw.setTransformationMethod (PasswordTransformationMethod.getInstance())


//                sign_edit_pw.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD)
            }
        }


        sign_rd_agree.setOnClickListener {
            if(sign_rd_agree.isSelected)
            {
                sign_rd_agree.setSelected(false)
                sign_rg_agree.clearCheck()
            }
            else
            {
                sign_rd_agree.setSelected(true)
                sign_rd_agree.setChecked(true)
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("sign_in",requestCode.toString() + "!!!!")
        if(requestCode == Constant.REQUEST_IMAGE)
        {
            if(profile_bitmap == null) {
                profile_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data!!.data)
                glideModule.load(profile_bitmap)
                        .apply(RequestOptions().circleCrop())
                        .into(sign_im_profile)
            }
        }
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
            startActivity(Intent(this,DefaultActivity::class.java))
        }

    }
    val receiver = object : BroadcastReceiver()
    {
        override fun onReceive(contxt: Context?, intent: Intent?) {

        }
    }

    override fun goLogin() {
        val intent = Intent(this,LoginActivity::class.java)
        intent.putExtra("first","first");
        startActivity(intent)
        finish()
    }

    override fun setDialog(message: String, type : Int) {

        AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(resources.getString(R.string.check), DialogInterface.OnClickListener
                { dialog, which ->

                    if(type == Constant.DIALOG_SIGN)
                    {
                        goLogin()
                    }
                    else if(type == Constant.DIALOG_OVER_ID)
                    {
                        idCheck = false
                        clearEditText()
                    }
                    else if(type == Constant.DIALOG_OVER_NOT_ID)
                    {
                        idCheck = true
                    }
                    else if(type == Constant.DIALOG_OVER_NOT_NICK)
                    {
                        nickCheck = true
                    }
                    else if(type == Constant.DIALOG_OVER_NICK)
                    {
                        nickCheck = false
                        clearEditText()
                    }

                        return@OnClickListener
                }
                ).show()

    }

    override fun onResume() {
        super.onResume()
        Log.d("my_login","onResume")
    }

    override fun startIntent(status: Int) {
        Log.d("login_at","블루투스 활성화")
        if (status == BluetoothService.get.REQUEST_ENABLE_BT) {
            Log.d("login_at","블루투스 활성화 가능하다")
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent, status)
        } else if (status == BluetoothService.get.REQUEST_CONNECT_DEVICE) {
            val intent = Intent(this, DeviceListActivity::class.java)
            startActivityForResult(intent, status)
        }

    }

}
