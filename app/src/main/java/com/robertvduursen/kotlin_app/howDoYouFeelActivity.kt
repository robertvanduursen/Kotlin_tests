package com.robertvduursen.kotlin_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_how_do_you_feel.*

class howDoYouFeelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_do_you_feel)

        btn_home_screen3.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
