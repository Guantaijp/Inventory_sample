package dev.guantel.myat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.guantel.myat.MainActivity.PreferenceHelper.age
import dev.guantel.myat.MainActivity.PreferenceHelper.name
import dev.guantel.myat.MainActivity.PreferenceHelper.number
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetails : AppCompatActivity(), View.OnClickListener {

    val CUSTOM_PREF_NAME = "User_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        btnShow.setOnClickListener(this)



    }

    override fun onClick(V: View?) {
        val prefs = MainActivity.PreferenceHelper.customPreference(this, CUSTOM_PREF_NAME)
        when (V?.id){

            R.id.btnShow -> {

                txt_name.text = prefs.name
                txt_age.text= prefs.age.toString()
                txt_number.text= prefs.number.toString()

            }

            }

    }
}