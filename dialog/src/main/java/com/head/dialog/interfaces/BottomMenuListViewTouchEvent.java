package com.head.dialog.interfaces;

import android.view.MotionEvent;

/**
*
* 类名称：BottomMenuListViewTouchEvent.java <br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:14 PM <br/>
* @version
*/
public abstract class BottomMenuListViewTouchEvent {
    
    public void down(MotionEvent event){};
    public void move(MotionEvent event){};
    public void up(MotionEvent event){};
}
