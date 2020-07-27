package com.robertvduursen.kotlin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_where_are_you.*
import android.content.Intent

class whereAreYouActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_where_are_you)



        btn_home_screen4.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
