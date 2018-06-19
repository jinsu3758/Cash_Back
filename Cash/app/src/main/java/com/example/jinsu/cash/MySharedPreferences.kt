package com.example.jinsu.cash

import android.content.Context
import android.content.SharedPreferences
import com.example.jinsu.cash.model.User
import com.google.gson.Gson

class MySharedPreferences (context : Context)
{

    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_EDITTEXT = "user_data"
    var gson : Gson = Gson()

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    /* 파일 이름과 EditText를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */


    var user_data: User?
        get() {
            if (prefs.getString(PREF_KEY_MY_EDITTEXT, "") != null) {
                return gson.fromJson(prefs.getString(PREF_KEY_MY_EDITTEXT, ""), User::class.java!!)
            } else
                return null
        }
        //get() = gson.fromJson(prefs.getString(PREF_KEY_MY_EDITTEXT, ""),User::class.java!!)
        set(value) = prefs.edit().putString(PREF_KEY_MY_EDITTEXT, gson.toJson(value)).apply()
    /* get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 ""
     * set(value) 실행 시 value로 값을 대체한 후 저장 */
}