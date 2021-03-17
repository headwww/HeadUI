package com.head.dialog.util.views.wheelview.timer;

import android.os.Handler;
import android.os.Message;

import com.head.dialog.util.views.wheelview.view.WheelView;


/**
*
* 类名称：MessageHandler.java <br/>
* 类描述：消息类<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:13 PM <br/>
* @version
*/
public final class MessageHandler extends Handler {
    public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
    public static final int WHAT_SMOOTH_SCROLL = 2000;
    public static final int WHAT_ITEM_SELECTED = 3000;

    private final WheelView wheelView;

    public MessageHandler(WheelView wheelView) {
        this.wheelView = wheelView;
    }

    @Override
    public final void handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_INVALIDATE_LOOP_VIEW:
                wheelView.invalidate();
                break;

            case WHAT_SMOOTH_SCROLL:
                wheelView.smoothScroll(WheelView.ACTION.FLING);
                break;

            case WHAT_ITEM_SELECTED:
                wheelView.onItemSelected();
                break;
        }
    }

}
