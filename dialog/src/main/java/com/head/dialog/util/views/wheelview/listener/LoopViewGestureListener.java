package com.head.dialog.util.views.wheelview.listener;

import android.view.MotionEvent;

import com.head.dialog.util.views.wheelview.view.WheelView;


/**
*
* 类名称：LoopViewGestureListener.java <br/>
* 类描述：手势监听<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:12 PM <br/>
* @version
*/
public final class LoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    private final WheelView wheelView;


    public LoopViewGestureListener(WheelView wheelView) {
        this.wheelView = wheelView;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        wheelView.scrollBy(velocityY);
        return true;
    }
}
