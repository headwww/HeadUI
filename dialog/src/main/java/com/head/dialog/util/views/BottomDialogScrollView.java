package com.head.dialog.util.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
*
* 类名称：BottomDialogScrollView.java <br/>
* 类描述：底部缩身弹窗<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:10 PM <br/>
* @version
*/
public class BottomDialogScrollView extends ScrollView {

    public BottomDialogScrollView(Context context) {
        super(context);
    }

    public BottomDialogScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomDialogScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottomDialogScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    boolean lockScroll;

    public void lockScroll(boolean lockScroll) {
        this.lockScroll = lockScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (lockScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
