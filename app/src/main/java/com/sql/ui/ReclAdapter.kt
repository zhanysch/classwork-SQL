package com.sql.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sql.Data.DataEdtcs
import com.sql.R
import kotlinx.android.synthetic.main.item_recycler.view.*

class ReclAdapter : RecyclerView.Adapter<ReclAdapter.VHolder>() {
    private var data = arrayListOf<DataEdtcs>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent,false)
        return VHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun update (data: ArrayList<DataEdtcs>){
        this.data=data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(data[position])
    }



    class VHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(data:DataEdtcs){
            itemView.tv1.text=data.EdtOne
            itemView.tv2.text=data.EdtTwo
            itemView.tv3.text=data.EdtThree
            itemView.tv4.text=data.EdtFour
        }
    }


}





