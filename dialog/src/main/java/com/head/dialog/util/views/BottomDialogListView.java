package com.head.dialog.util.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;

import com.head.dialog.dialogs.BottomDialog;
import com.head.dialog.interfaces.BottomMenuListViewTouchEvent;


public class BottomDialogListView extends ListView {
    
    private BottomMenuListViewTouchEvent bottomMenuListViewTouchEvent;
    
    public BottomDialogListView(Context context) {
        super(context);
    }
    
    public BottomDialogListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public BottomDialogListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    private BottomDialog.DialogImpl dialogImpl;
    
    public BottomDialogListView(BottomDialog.DialogImpl dialog, Context context) {
        super(context);
        dialogImpl = dialog;
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    
    private int mPosition;
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked = ev.getActionMasked() & MotionEvent.ACTION_MASK;
        
        if (actionMasked == MotionEvent.ACTION_DOWN) {
            if (bottomMenuListViewTouchEvent != null) {
                bottomMenuListViewTouchEvent.down(ev);
            }
            mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            return super.dispatchTouchEvent(ev);
        }
        
        if (actionMasked == MotionEvent.ACTION_MOVE) {
            if (bottomMenuListViewTouchEvent != null) {
                bottomMenuListViewTouchEvent.move(ev);
            }
            return true;
        }
        if (actionMasked == MotionEvent.ACTION_UP || actionMasked == MotionEvent.ACTION_CANCEL) {
            if (bottomMenuListViewTouchEvent != null) {
                bottomMenuListViewTouchEvent.up(ev);
            }
            if (pointToPosition((int) ev.getX(), (int) ev.getY()) == mPosition) {
                super.dispatchTouchEvent(ev);
            } else {
                setPressed(false);
                invalidate();
            }
        }
        
        return super.dispatchTouchEvent(ev);
    }
    
    public BottomMenuListViewTouchEvent getBottomMenuListViewTouchEvent() {
        return bottomMenuListViewTouchEvent;
    }
    
    public BottomDialogListView setBottomMenuListViewTouchEvent(BottomMenuListViewTouchEvent bottomMenuListViewTouchEvent) {
        this.bottomMenuListViewTouchEvent = bottomMenuListViewTouchEvent;
        return this;
    }
}
