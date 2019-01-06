package com.zhou.customlayoutmanage.layoutmanage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhou on 2018/12/22.
 */
public class CircleLayoutManager extends LinearLayoutManager {
    public CircleLayoutManager(Context context) {
        super(context);
    }

    public CircleLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CircleLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    double r = Math.PI / 8;
    static int R;
    double offset = 0;
    int top = 60;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e("555555", "onLayoutChildren ");
        if (getItemCount() == 0) return;
        removeAndRecycleAllViews(recycler);
        R = getWidth() / 2;
        layoutChild(recycler);

    }

    int indexOffset = 0;
    RecyclerView.Recycler mRecycler;

    private void layoutChild(RecyclerView.Recycler recycler) {
        if (mRecycler == null) {
            mRecycler = recycler;
        }

        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt == null) continue;
                measureChildWithMargins(childAt, 0, 0);
                int left = childAt.getLeft();
                int top = childAt.getTop();

                if (left < childAt.getWidth() / 2 && top >= (getHeight() - 5)) {
                    removeAndRecycleView(childAt, recycler);
                    indexOffset++;
                    if (indexOffset > 2){
                        indexOffset = 2;
                    }

                }

                if (left > getWidth() / 2 && top >= getHeight()) {
                    removeAndRecycleView(childAt, recycler);
                    indexOffset--;
                    if (indexOffset < 0) {
                        indexOffset = 0;
                    }
                }
                Log.e("2222222", "left " + left + " top: " + top + " width " + getWidth() + " height " + getHeight());
            }

        }
        Log.e("111111", "child count " + childCount);

        detachAndScrapAttachedViews(recycler);
        for (int i = 0; i < 9; i++) {
            if (4 + count > 100) return;
            View view = recycler.getViewForPosition(i + indexOffset);
            measureChildWithMargins(view, 0, 0);
            int viewWidth = getDecoratedMeasuredWidth(view);
            int viewHeight = getDecoratedMeasuredHeight(view);
            int left = (int) ((getWidth() - viewWidth) / 2 + Math.sin((i + indexOffset) * r + offset) * R);
            int height = getHeight();
            int top = (int) (height - Math.cos((i + indexOffset) * r + offset) * getHeight());
            // int top = (int) (i * Math.cos(i*r )*getHeight());
            //  top = i%4 * 60;
            int right = left + viewWidth;
            int bottom = top + viewHeight;
            addView(view);
            layoutDecoratedWithMargins(view, left, top, right, bottom);
            // layoutDecorated(view,left,top,right,bottom);

        }
    }

    int count = 0;

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        offset += ((-dx / (Math.sin(r) * R)) * r);
        if (Math.abs(dx) >= r) {
            // count ++;
            Log.e("test", "count ++ ");
        }

        if (offset <= -10 * r) {
            offset = -10 * r;
        }
        if (offset > 0) {
            offset = 0;
        }


        Log.e("999999", "dx " + dx + " offset: " + offset + " 3r" + 3 * r);
        layoutChild(recycler);
        Log.e("555555", "scrollHorizontallyBy ");


        return dx;
    }

    double oldOffset;
    double endOffset;
    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE){
            oldOffset = offset;
           int index = (int) (Math.abs(offset) /r + 0.5f) ;
           Log.e("777777","index: " + index);
//           if (index > 11){
//               index = 11;
//           }
            endOffset = - index * r;
            ValueAnimator animator = ValueAnimator.ofFloat((float) oldOffset,(float) endOffset);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                  offset = Double.parseDouble(animation.getAnimatedValue() +"") ;
                 //   offset = Double.parseDouble(animation.getAnimatedValue()()+"");
                    layoutChild(mRecycler);
                }
            });
            animator.setDuration(200);
            animator.start();

        }
    }



}
