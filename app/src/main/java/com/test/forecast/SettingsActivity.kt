package com.test.forecast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val buttonsave: Button = this.findViewById<Button>(R.id.btnsave) as Button
        val spinner1: Spinner = this.findViewById<Spinner>(R.id.cc1) as Spinner
        val spinner2: Spinner = this.findViewById<Spinner>(R.id.cc2) as Spinner
        val spinner3: Spinner = this.findViewById<Spinner>(R.id.cc3) as Spinner
        val et1: EditText = this.findViewById<EditText>(R.id.cname1) as EditText
        val et2: EditText = this.findViewById<EditText>(R.id.cname2) as EditText
        val et3: EditText = this.findViewById<EditText>(R.id.cname3) as EditText

        val ct1 = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity).getString("c1", "")
        val ct2 = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity).getString("c2", "")
        val ct3 = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity).getString("c3", "")
        val s1 = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity).getInt("sp1", 0)
        val s2 = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity).getInt("sp2", 0)
        val s3 = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity).getInt("sp3", 0)

spinner1.setSelection(s1)
spinner2.setSelection(s2)
spinner3.setSelection(s3)
        et1.setText(ct1)
        et2.setText(ct2)
        et3.setText(ct3)
        buttonsave.setOnClickListener { view ->
          /*  val intent = Intent(this, SettingsActivity::class.java)
            this.startActivity(intent)*/
            if(et1.text.trim().toString().length<1){
                Toast.makeText(this,"Please Enter City name",Toast.LENGTH_SHORT).show()
            }
         val sp1=   spinner1.selectedItemPosition
         val sp2=   spinner2.selectedItemPosition
         val sp3=   spinner3.selectedItemPosition
            val city1=et1.text.toString()
            val city2=et2.text.toString()
            val city3=et3.text.toString()

            val   mcodes = getResources().getStringArray(R.array.array_codes)
            val code=mcodes[sp1]
            PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("sp1", sp1).apply()
            PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("sp2", sp2).apply()
            PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("sp3", sp3).apply()
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("c1", city1).apply()
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("c2", city2).apply()
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("c3", city3).apply()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()
            finish()


        }


    }
}
