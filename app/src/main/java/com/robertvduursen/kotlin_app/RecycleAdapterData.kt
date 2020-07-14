package com.robertvduursen.kotlin_app

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
//import android.widget.Adapter
import android.widget.TextView
import com.robertvduursen.kotlin_app.database.myActivities
import kotlinx.android.synthetic.main.layout_list_data.view.*
import java.util.ArrayList
import android.view.View

//class RecyclerAdapterPeople: Adapter<RecyclerView.ViewHolder>() {
class RecycleAdapterData: Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    //submitList
    private var items: List<myActivities> = ArrayList()

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val id_number: TextView = itemView.data_id
        private val firstname: TextView = itemView.data_name

        fun bind(people_obj: myActivities) {
            id_number.setText(people_obj.id)
            firstname.setText(people_obj.fname)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_data, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder ->{
                holder.bind(items.get(position))
            }

        }
    }


    override fun getItemCount(): Int {
        return items.size;
    }


    fun submitList(peopleList: List<myActivities>){
        items = peopleList
    }
}