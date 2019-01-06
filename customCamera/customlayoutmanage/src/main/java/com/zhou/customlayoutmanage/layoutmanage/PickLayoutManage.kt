package com.zhou.customlayoutmanage.layoutmanage

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

/**
 * Created by zhou on 2018/12/9.
 */
class PickLayoutManage : LinearLayoutManager {
    val TAG = "PickLayoutManage"
    var count = 5
    var linearsnapHelp: LinearSnapHelper? = null

    constructor(context: Context) : this(context, OrientationHelper.VERTICAL, false)
    constructor(context: Context, orientation: Int, boolean: Boolean) : super(context, orientation, boolean)

    init {
        linearsnapHelp = LinearSnapHelper()
        isAutoMeasureEnabled = false
    }

    override fun onAttachedToWindow(view: RecyclerView?) {
        super.onAttachedToWindow(view)

        linearsnapHelp?.attachToRecyclerView(view)
    }

    override fun onMeasure(recycler: RecyclerView.Recycler?, state: RecyclerView.State?, widthSpec: Int, heightSpec: Int) {
        var itemCount = itemCount
        if (itemCount == 0 || state?.itemCount == 0) {
            Log.e(TAG, "itemCount: $itemCount")
            super.onMeasure(recycler, state, widthSpec, heightSpec)
        } else {
            var view = recycler?.getViewForPosition(0)
            measureChildWithMargins(view,widthSpec,heightSpec)
//            var viewWidth = getDecoratedMeasuredWidth(view)
//            var viewHeight = getDecoratedMeasuredHeight(view)
            var viewWidth = view?.measuredWidth
            var viewHeight = view?.measuredHeight
            var viewTotalHeight = 0
            var heightMode = View.MeasureSpec.getMode(heightSpec)
            var widthMode = View.MeasureSpec.getMode(widthSpec)
            if (heightMode == View.MeasureSpec.AT_MOST) {
                viewTotalHeight = viewHeight!!*count
            }

            if (widthMode == View.MeasureSpec.AT_MOST){

            }
            setMeasuredDimension(viewWidth!!, viewTotalHeight!!)
        }

    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        offsetCHild()
        Log.e("2222222","scrollv= vertical")
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        if (itemCount < 0 || state!!.isPreLayout) return
        offsetCHild()
    }

    private fun offsetCHild() {
       var midHegiht = height*3 /5
        var count = childCount
        for (i: Int in 0..count) {
            var view = getChildAt(i)
            //  var centerView = linearsnapHelp?.findSnapView(this)
            // getTopDecorationHeight() Returns the total height of item decorations applied to child's top
            //    var topDecorationHeight = getTopDecorationHeight(centerView)
            var childAt = getChildAt(i)
            if (childAt == null){
                break
            }
            var decoratedBottom = getDecoratedBottom(childAt)

            if (midHegiht == decoratedBottom){
                childAt.alpha =0.5f
            }else {
                childAt.alpha = 1.0f
            }

//            var decoratedTop = getDecoratedTop(childAt)
//            var decoratedBottom = getDecoratedBottom(childAt)
//            var topDecorationHeight = getTopDecorationHeight(childAt)
//            var bottomDecorationHeight = getBottomDecorationHeight(childAt)
//            Log.e("test333","i: $i ,decoratedTop: $decoratedTop ,decoratedBottom:$decoratedBottom" +
//                    " ，topDecorationHeight: $topDecorationHeight ,bottomDecorationHeight: $bottomDecorationHeight" +
//                    "")
        }
    }
}