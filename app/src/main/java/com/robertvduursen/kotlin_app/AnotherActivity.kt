package com.robertvduursen.kotlin_app

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_another.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

// https://expertise.jetruby.com/how-to-implement-motion-sensor-in-a-kotlin-app-b70db1b5b8e5


class AnotherActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager : SensorManager ?= null
    private var accelerometer : Sensor ?= null
//    private lateinit var sensorManager: SensorManager
//    private lateinit var accelerometer: Sensor
//    private var SensorManager : SensorManager ?= null

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


    override fun onSensorChanged(event: SensorEvent?) {
        when (event?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> {
//                acceleromter_data.text = "lol = %.2f".format(event.values[0])

                val alpha = 0.8f
                var accelGravity = 1.0f

                accelGravity = alpha * accelGravity + (1 - alpha) * event.values[0]
                var accelLin = event.values[0] - accelGravity

                acceleromter_data.text = accelLin.toString()
//                acceleromter_data.text = event.values[0].toString()
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

//        sensorManager.registerListener(
//            this,
//            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//            SensorManager.SENSOR_DELAY_NORMAL
//        )

//        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        sensorManager.registerListener(this, this.accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
//            this.accelerometer = it
//        }



        btnBackHomeActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }


    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,accelerometer,
            SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

}

//btnBackHomeActivity