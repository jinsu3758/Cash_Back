package com.example.jinsu.cash.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jinsu.cash.R
import com.example.jinsu.cash.common.Constant
import com.example.jinsu.cash.dialog.PopupDialog
import com.example.jinsu.cash.util.CircleAnimation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.navi_header.view.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        setAnimation()
    }

    @SuppressLint("ResourceAsColor")
    fun setAnimation()
    {
        val animation = CircleAnimation(content_main.main_circle,260, 80)
        animation.duration = 3000
        content_main.main_circle.startAnimation(animation)
//        main_toolbar.navigationIcon = getDrawable(R.drawable.menu)

    }


    fun init()
    {
        main_toolbar.setNavigationIcon(getDrawable(R.drawable.menu))
        content_main.main_toolbar.title = getString(R.string.main)
        content_main.main_toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(content_main.main_toolbar)
        val toggle = ActionBarDrawerToggle(this,main_layout,content_main.main_toolbar
                ,0,0)
        main_layout.addDrawerListener(toggle)
        toggle.syncState()
        main_navi.setNavigationItemSelectedListener(this)

        Glide.with(this).load(R.drawable.my).apply(RequestOptions().circleCrop())
                .into(main_navi.getHeaderView(0).navi_im_profile)
        if(Constant.prefs.user_data != null)
        {
            val user = Constant.prefs.user_data
            Log.d("main_at",user!!.nickname)
            main_navi.getHeaderView(0).navi_txt_id.setText(user!!.id.toString())
            content_main.main_txt_point.setText(user!!.point.toString())
        }
    }



    override fun onBackPressed() {
        if (main_layout.isDrawerOpen(GravityCompat.START)) {
            main_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_nav_home ->
            {
                val dialog = PopupDialog(this, "다리 꼬지 마세요.")
                dialog.show()
                dialog.setClick {
                    startActivity(Intent(this,MyPageActivity::class.java))
                }
                /*val intent = Intent(this,PopUpActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)*/
            }
            R.id.menu_nav_mypage ->
            {
                startActivity(Intent(this,MyPageActivity::class.java))
            }
            R.id.menu_nav_chart ->
            {
                startActivity(Intent(this,ChartActivity::class.java))
            }
            R.id.menu_nav_shopping ->
            {
                startActivity(Intent(this,ShopActivity::class.java))
            }
            R.id.menu_nav_rank ->
            {
                startActivity(Intent(this,RankActivity::class.java))
            }
        }
        main_layout.closeDrawer(GravityCompat.START)
        return true
    }



}
