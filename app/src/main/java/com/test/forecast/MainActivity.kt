package com.test.forecast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.Toast
import com.test.forecast.*;
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonadd: Button = this.findViewById<Button>(R.id.add) as Button
        val btncurrent: Button = this.findViewById<Button>(R.id.current) as Button
        buttonadd.setOnClickListener { view ->
            val intent = Intent(this, SettingsActivity::class.java)
            this.startActivity(intent)

        }
  btncurrent.setOnClickListener { view ->
            val intent = Intent(this, CurrentActivity::class.java)
            this.startActivity(intent)

        }


    }


}

