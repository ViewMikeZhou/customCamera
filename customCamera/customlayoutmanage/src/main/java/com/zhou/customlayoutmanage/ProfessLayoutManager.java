package com.zhou.customlayoutmanage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhou on 2018/12/13.
 */
public class ProfessLayoutManager extends LinearLayoutManager {


    public ProfessLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        setAutoMeasureEnabled(false);
    }

    public ProfessLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAutoMeasureEnabled(false);
    }


    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int widthMode = View.MeasureSpec.getMode(widthSpec);
        int heightMode = View.MeasureSpec.getMode(heightSpec);
        int wdithSize = View.MeasureSpec.getSize(widthSpec);
        int heightSize = View.MeasureSpec.getSize(heightSpec);

        int viewWidth = 0;
        int itemCount = getItemCount();
        if (itemCount == 0) {
            super.onMeasure(recycler,state,widthSpec,heightSpec);

        }else{
            if (widthMode == View.MeasureSpec.AT_MOST) {
                Log.e("test","item count:"+ itemCount);
                int itemViewWidth = 0;
                int itemViewHegith = 0;
                for (int i = 0; i < itemCount; i++) {
                    View itemView = recycler.getViewForPosition(0);
                    itemView.measure(0, 0);
                    int measuredWidth = itemView.getMeasuredWidth();
                    itemViewWidth = Math.max(viewWidth, measuredWidth) + itemView.getPaddingLeft() + itemView.getPaddingRight();
                    itemViewHegith = itemView.getMeasuredHeight() + itemView.getPaddingTop() + itemView.getPaddingBottom();
                }
                viewWidth = 3 * itemViewWidth;
                setMeasuredDimension(viewWidth,itemViewHegith);
            }else{
                Log.e("test","item count:"+ itemCount);
                int itemViewWidth = 0;
                int itemViewHegith = 0;
                for (int i = 0; i < itemCount; i++) {
                    View itemView = recycler.getViewForPosition(i);
                    itemView.measure(0, 0);
                    int measuredWidth = itemView.getMeasuredWidth();
                    itemViewWidth = Math.max(viewWidth, measuredWidth) + itemView.getPaddingLeft() + itemView.getPaddingRight();
                    itemViewHegith = itemView.getMeasuredHeight() + itemView.getPaddingTop() + itemView.getPaddingBottom();
                }
                viewWidth = 3 * itemViewWidth;
                setMeasuredDimension(viewWidth,itemViewHegith);
            }
        }


    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e("3333333333","child count " + getChildCount());
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public boolean isSmoothScrolling() {
        return super.isSmoothScrolling();
    }
}
