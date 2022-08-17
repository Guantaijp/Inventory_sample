package dev.guantel.myat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import dev.guantel.myat.MainActivity.PreferenceHelper.age
import dev.guantel.myat.MainActivity.PreferenceHelper.customPreference
import dev.guantel.myat.MainActivity.PreferenceHelper.defaultPreference
import dev.guantel.myat.MainActivity.PreferenceHelper.name
import dev.guantel.myat.MainActivity.PreferenceHelper.number

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_details.*


class MainActivity : AppCompatActivity(),View.OnClickListener {

    val CUSTOM_PREF_NAME = "User_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener(this)
        btnNext.setOnClickListener(this)


        val defaultPrefs = defaultPreference(this)

        defaultPrefs.name="toszh"
        defaultPrefs.age=24
        defaultPrefs.number=2222


    }

    override fun onClick(v: View?) {
        val prefs =  customPreference(this, CUSTOM_PREF_NAME)
        when (v?.id){
            R.id.btnSave -> {
                prefs.name =et_name.text.toString()
                prefs.age=et_age.text.toString().toInt()
                prefs.number=et_number.text.toString().toInt()

            }
            R.id.btnShow -> {

                txt_name.text = prefs.name
                txt_age.text= prefs.age.toString()
                txt_number.text= prefs.number.toString()

            }

            R.id.btnNext -> {
                startActivity(Intent(this, UserDetails::class.java))
        }
    }
}
object PreferenceHelper {

    val USER_NAME = "USER_NAME"
    val USER_AGE = "USER_AGE"
    val USER_NUMBER = "USER_NUMBER"


    fun defaultPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    inline fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in SharedPreferences")
        }
    }

    var SharedPreferences.name
        get() = getString(USER_NAME, "")
        set(value) {
            editMe {
                it.putString(USER_NAME, value)
            }
        }


    var SharedPreferences.age
        get() = getInt(USER_AGE, 0)
        set(value) {
            editMe {
                it.putInt(USER_AGE, value)
            }
        }
    var SharedPreferences.number
        get() = getInt(USER_NUMBER, 0)
        set(value) {
            editMe {
                it.putInt(USER_NUMBER, value)
            }
        }
}}

