package com.robertvduursen.kotlin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*


import com.robertvduursen.kotlin_app.database.myActivities
import kotlinx.android.synthetic.main.activity_what_are_you_doing.*


class whatAreYouDoingActivity : AppCompatActivity() {

    var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_are_you_doing)

        btn_home_screen2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val peopleList: List<myActivities> = dbHelper.readAllData()
        val _personNames = arrayOf("Rul", "Jak", "Rajeev", "Aryan", "Rashmi", "Jaspreet", "Akbar")

        for (x in 0 until peopleList.size-1){
            _personNames[x] = peopleList[x].fname
        }

        val spinner = findViewById<Spinner>(R.id.doing_spinner)
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, _personNames)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(
                        this@whatAreYouDoingActivity,
                        getString(R.string.selected_item) + " " + _personNames[position],
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }


        }
    }

}
