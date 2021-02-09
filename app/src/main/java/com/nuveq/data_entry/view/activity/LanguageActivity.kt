package com.nuveq.data_entry.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nuveq.data_entry.R
import com.nuveq.data_entry.common.App

class LanguageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        findViewById<Button>(R.id.en).setOnClickListener {
            App.instance.setLangSwitch(false)
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        findViewById<Button>(R.id.bn).setOnClickListener {
            App.instance.setLangSwitch(true)
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))

        }
    }
}