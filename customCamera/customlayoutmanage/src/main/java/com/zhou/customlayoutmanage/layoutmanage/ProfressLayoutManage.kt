package com.zhou.customlayoutmanage.layoutmanage

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.util.Log
import kotlinx.android.synthetic.main.my_adapter_item.view.*

/**
 * Created by zhou on 2018/12/12.
 */
class ProfressLayoutManage:LinearLayoutManager{

    var snapHelper:SnapHelper
    constructor(context: Context,snapHelper: SnapHelper) : this(context, OrientationHelper.VERTICAL, false,snapHelper)
    constructor(context: Context, orientation: Int, boolean: Boolean,snapHelper: SnapHelper) : super(context, orientation, boolean){
        this.snapHelper = snapHelper
    }


    override fun onAttachedToWindow(view: RecyclerView?) {
        snapHelper.attachToRecyclerView(view)
        super.onAttachedToWindow(view)
    }

    override fun onMeasure(recycler: RecyclerView.Recycler?, state: RecyclerView.State?, widthSpec: Int, heightSpec: Int) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {

        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    override fun onScrollStateChanged(state: Int) {
        Log.e("3333333333333333333","childcount $childCount")
        if (state == 0){

            var findSnapView = snapHelper.findSnapView(this)
            findSnapView?.tv_item?.setTextColor(Color.RED)
            var position = getPosition(findSnapView)
            Log.e("test2222","选中的中间位置 ${findSnapView?.tv_item?.text}")
        }
    }

}