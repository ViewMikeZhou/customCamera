package com.zhou.customlayoutmanage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.zhou.customlayoutmanage.layoutmanage.CircleLayoutManager
import com.zhou.customlayoutmanage.layoutmanage.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = ArrayList<String>()
        for (i: Int in 0..13) {
            list.add("test0")
        }
        recycle_view.adapter = MyAdapter(list)
        recycle_view.layoutManager = CircleLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        var snapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(recycle_view)
        Log.e(TAG, "height: ${recycle_view.layoutManager.height}")


    }
}
