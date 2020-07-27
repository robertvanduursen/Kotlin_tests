package com.robertvduursen.kotlin_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlinx.android.synthetic.main.activity_database_view.*
import kotlinx.android.synthetic.main.activity_main.*

//import kotlinx.android.synthetic.main.activity_recycler.*


class DatabaseView : AppCompatActivity() {

    private lateinit var dataAdapter: RecycleAdapterData
    var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_view)

        btn_home_screen.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        add_data.setOnClickListener{ addData()}
        //add_data


        initRecyclerView()
        dataAdapter.submitList(dbHelper.readAllData())


    }

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@DatabaseView)
            addItemDecoration(SpaceDecoration(5))
            dataAdapter = RecycleAdapterData()
            adapter = dataAdapter
        }


    }

    fun addData(){
        // talk to database
        val fname = this.et_fname.text.toString()
        val result = dbHelper.insertData(fname = fname)

        // maintenance
        clearfields()
        this.tv_result.text = ("Added user: " + result)

    }

    private fun clearfields(){
//        et_id.setText("")
        et_fname.setText("")
    }


}
