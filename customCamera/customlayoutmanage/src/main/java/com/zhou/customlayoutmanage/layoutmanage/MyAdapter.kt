package com.zhou.customlayoutmanage.layoutmanage

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zhou.customlayoutmanage.R
import kotlinx.android.synthetic.main.my_adapter_item.view.*

/**
 * Created by zhou on 2018/12/8.
 */
class MyAdapter(dataList:List<String>):RecyclerView.Adapter<MyAdapter.MyViewHolde>(){
    private var dataList:List<String>
    init {
        Log.e("test111","MyAdapter init")
       this.dataList = dataList
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolde {
        Log.e("test111","MyAdapter onCreateViewHolder")
       var view =  LayoutInflater.from(parent?.context).inflate(R.layout.my_adapter_item,parent,false)
        return MyViewHolde(view)
    }

    override fun getItemCount(): Int {
        Log.e("test111","MyAdapter getItemCount")
        return dataList.size
    }



    override fun onBindViewHolder(holder: MyViewHolde?, position: Int) {
        Log.e("test111","MyAdapter getItemCount === 1111")

        holder?.text?.text = dataList[position]
    }

    class MyViewHolde(itemView:View):RecyclerView.ViewHolder(itemView){
       var text: TextView? = null
       init {
          text = itemView.tv_item
       }
    }




}