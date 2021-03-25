package com.head.dialog.dialogs;

import android.app.Activity;


import java.lang.ref.WeakReference;

/**
*
* 类名称：TipDialog.java <br/>
* 类描述：提醒弹窗<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 10:45 PM <br/>
* @version
*/
public class TipDialog extends WaitDialog {

    protected TipDialog() {
        super();
    }

    public static WaitDialog show(int messageResId) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        if (dialogImpl != null) {
            dialogImpl.showTip(TYPE.WARNING);
        } else {
            me().showTip(messageResId, TYPE.WARNING);
        }
        return me();
    }

    public static WaitDialog show(Activity activity, int messageResId) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.showTip(TYPE.WARNING);
        } else {
            me().showTip(activity, messageResId, TYPE.WARNING);
        }
        return me();
    }

    public static WaitDialog show(CharSequence message) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(message);
        if (dialogImpl != null) {
            dialogImpl.showTip(TYPE.WARNING);
        } else {
            me().showTip(message, TYPE.WARNING);
        }
        return me();
    }

    public static WaitDialog show(Activity activity, CharSequence message) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(message);
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.showTip(TYPE.WARNING);
        } else {
            me().showTip(activity, message, TYPE.WARNING);
        }
        return me();
    }

    public static WaitDialog show(int messageResId, TYPE tip) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        if (dialogImpl != null) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(messageResId, tip);
        }
        return me();
    }

    public static WaitDialog show(Activity activity, int messageResId, TYPE tip) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(activity, messageResId, tip);
        }
        return me();
    }

    public static WaitDialog show(CharSequence message, TYPE tip) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(message);
        if (dialogImpl != null) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(message, tip);
        }
        return me();
    }

    public static WaitDialog show(Activity activity, CharSequence message, TYPE tip) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(message);
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(activity, message, tip);
        }
        return me();
    }

    public static WaitDialog show(int messageResId, TYPE tip, long duration) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        me().tipShowDuration = duration;
        if (dialogImpl != null) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(messageResId, tip);
        }
        return me();
    }

    public static WaitDialog show(Activity activity, int messageResId, TYPE tip, long duration) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        me().tipShowDuration = duration;
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(activity, messageResId, tip);
        }
        return me();
    }

    public static WaitDialog show(CharSequence message, TYPE tip, long duration) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(message);
        me().tipShowDuration = duration;
        if (dialogImpl != null) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(message, tip);
        }
        return me();
    }

    public static WaitDialog show(Activity activity, CharSequence message, TYPE tip, long duration) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(message);
        me().tipShowDuration = duration;
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.showTip(tip);
        } else {
            me().showTip(activity, message, tip);
        }
        return me();
    }

    @Override
    public String dialogKey() {
        return getClass().getSimpleName() + "(" + Integer.toHexString(hashCode()) + ")";
    }
}
