package ltd.v2.starTrekAi.storage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("StaticFieldLeak")
object AppSessionManager {

    private  var _context: Context?=null

    //..............Merchant .........................
    val PREF_NAME = "PRODUCTIVITY_PREFERENCES"
    val TOKEN = "token"

    private  var sharedPreferences: SharedPreferences?=null
    private  var editor: SharedPreferences.Editor?=null

    fun init(context: Context) {
        if (sharedPreferences == null) {
            _context = context
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
            editor = sharedPreferences!!.edit()
        }
    }


    // Shared preference data store for Merchant token session
    fun setToken(token: String) {
        editor!!.putString(TOKEN, token)
        editor!!.apply()
    }

    fun getToken(): String {
        return sharedPreferences!!.getString(TOKEN, "") ?: ""
    }



}