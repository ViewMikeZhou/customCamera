package com.zhou.customlayoutmanage.layoutmanage

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by zhou on 2018/12/8.
 */
class CustomLayoutManage :LinearLayoutManager{
    val TAG:String = "CustomLayoutManage"
    init {
        Log.e("test111","CustomLayoutManage")

    }
    var itemeCount:Int = 0
    var totalOffset =0

    constructor(context:Context) : this(context,LinearLayoutManager.HORIZONTAL,false)


    constructor(context: Context,orientation:Int,reverseLayout:Boolean):super(context,orientation,reverseLayout){
        Log.e("test111","CustomLayoutManage多参被构造")

    }

    /**
     * setLayoutManage是调用
     */
    override fun onAttachedToWindow(view: RecyclerView?) {
        Log.e("test111","onAttachedToWindow")

        super.onAttachedToWindow(view)

    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        Log.e("test111","onLayoutChildren")
        super.onLayoutChildren(recycler, state)
    }

    override fun canScrollVertically(): Boolean {
        return super.canScrollVertically()
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
      var childeHeight =   getDecoratedMeasuredHeight(getChildAt(0))
      //  Log.e(TAG,"childeHeight: $childeHeight")
        itemeCount = itemCount
       var first = findFirstVisibleItemPosition()
       var last =  findLastCompletelyVisibleItemPosition()
        var visibleCount = last - first +1
        var total = (itemeCount-1)*childeHeight - visibleCount*childeHeight
        totalOffset += dy
        if (totalOffset == total){
            // 5550
            Log.e(TAG,"到底了")

        }
        Log.e(TAG,"first:$first ,last: $last total: $total , totalOffset：$totalOffset")

        return super.scrollVerticallyBy(dy, recycler, state)
    }

    /**
     *
     */
    override fun onScrollStateChanged(state: Int) {
        Log.e(TAG, "scroll stat: $state")
        super.onScrollStateChanged(state)
    }
}