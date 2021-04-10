package com.head.dialog.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.head.dialog.HeadDialog;
import com.head.dialog.impl.ActivityLifecycleImpl;
import com.head.dialog.style.DialogStyle;
import com.head.dialog.util.TextInfo;

import java.lang.ref.WeakReference;

import static com.head.dialog.HeadDialog.DEBUGMODE;

/**
*
* 类名称：BaseDialog.java <br/>
* 类描述：基础类<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:14 PM <br/>
* @version
*/
public abstract class BaseDialog {

    private static WeakReference<FrameLayout> rootFrameLayout;
    private static WeakReference<Activity> contextWeakReference;

    public static void init(Context context) {
        ActivityLifecycleImpl.init(context, new ActivityLifecycleImpl.onActivityResumeCallBack() {
            @Override
            public void getActivity(Activity activity) {
                try {
                    contextWeakReference = new WeakReference<>(activity);
                    rootFrameLayout = new WeakReference<>((FrameLayout) activity.getWindow().getDecorView());
                } catch (Exception e) {
                    error("Dialog.init: 初始化异常，找不到Activity的根布局");
                }
            }
        });
    }

    protected static void log(Object o) {
        if (DEBUGMODE) Log.i(">>>", o.toString());
    }

    protected static void error(Object o) {
        if (DEBUGMODE) Log.e(">>>", o.toString());
    }

    public abstract void onUIModeChange(Configuration newConfig);

    protected static void show(final View view) {
        if (rootFrameLayout == null || view == null || rootFrameLayout.get() == null) return;
        log(view.getTag() + ".show");

        runOnMain(new Runnable() {
            @Override
            public void run() {
                rootFrameLayout.get().addView(view);
            }
        });
    }

    protected static void show(Activity activity, final View view) {
        if (activity == null || view == null) return;
        if (activity.isDestroyed()) {
            error(view.getTag() + ".show ERROR: activity is Destroyed.");
            return;
        }
        log(view.getTag() + ".show");
        final FrameLayout activityRootView = (FrameLayout) activity.getWindow().getDecorView();
        if (activityRootView == null) {
            return;
        }
        runOnMain(new Runnable() {
            @Override
            public void run() {
                activityRootView.addView(view);
            }
        });
    }

    protected static void dismiss(final View dialogView) {
        log(dialogView.getTag() + ".dismiss");
        if (rootFrameLayout == null || dialogView == null) return;
        runOnMain(new Runnable() {
            @Override
            public void run() {
                if (dialogView.getParent() == null || !(dialogView.getParent() instanceof ViewGroup)) {
                    rootFrameLayout.get().removeView(dialogView);
                } else {
                    ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                }
            }
        });
    }

    public static Context getContext() {
        if (contextWeakReference == null) return null;
        return contextWeakReference.get();
    }

    public static void cleanContext() {
        contextWeakReference.clear();
        contextWeakReference = null;
        System.gc();
    }

    protected boolean cancelable = true;
    protected OnBackPressedListener onBackPressedListener;
    protected boolean isShow;
    protected DialogStyle style;
    protected HeadDialog.THEME theme;
    protected boolean autoShowInputKeyboard;
    protected int backgroundColor = -1;
    protected long enterAnimDuration = -1;
    protected long exitAnimDuration = -1;

    public BaseDialog() {
        cancelable = HeadDialog.cancelable;
        style = HeadDialog.globalStyle;
        theme = HeadDialog.globalTheme;
        enterAnimDuration = HeadDialog.enterAnimDuration;
        exitAnimDuration = HeadDialog.exitAnimDuration;
        autoShowInputKeyboard = HeadDialog.autoShowInputKeyboard;
    }

    public View createView(int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }

    public boolean isShow() {
        return isShow;
    }

    public DialogStyle getStyle() {
        return style;
    }

    public HeadDialog.THEME getTheme() {
        return theme;
    }

    protected void useTextInfo(TextView textView, TextInfo textInfo) {
        if (textInfo == null) return;
        if (textView == null) return;
        if (textInfo.getFontSize() > 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textInfo.getFontSize());
        }
        if (textInfo.getFontColor() != 1) {
            textView.setTextColor(textInfo.getFontColor());
        }
        if (textInfo.getGravity() != -1) {
            textView.setGravity(textInfo.getGravity());
        }
        textView.getPaint().setFakeBoldText(textInfo.isBold());
    }

    protected void showText(TextView textView, CharSequence text) {
        if (textView == null) return;
        if (isNull(text)) {
            textView.setVisibility(View.GONE);
            textView.setText("");
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        }
    }

    protected View createHorizontalSplitView(int color) {
        View splitView = new View(getContext());
        splitView.setBackgroundColor(color);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        splitView.setLayoutParams(lp);
        return splitView;
    }

    protected View createVerticalSplitView(int color, int height) {
        View splitView = new View(getContext());
        splitView.setBackgroundColor(color);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1, dip2px(height));
        splitView.setLayoutParams(lp);
        return splitView;
    }

    public static boolean isNull(String s) {
        if (s == null || s.trim().isEmpty() || "null".equals(s) || "(null)".equals(s)) {
            return true;
        }
        return false;
    }

    public static boolean isNull(CharSequence c) {
        String s = String.valueOf(c);
        if (c == null || s.trim().isEmpty() || "null".equals(s) || "(null)".equals(s)) {
            return true;
        }
        return false;
    }

    public Resources getResources() {
        if (getContext() == null) return Resources.getSystem();
        return getContext().getResources();
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public boolean isLightTheme() {
        if (theme == HeadDialog.THEME.AUTO) {
            return (getContext().getApplicationContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO;
        }
        return theme == HeadDialog.THEME.LIGHT;
    }

    public static FrameLayout getRootFrameLayout() {
        if (rootFrameLayout == null) return null;
        return rootFrameLayout.get();
    }

    public void tintColor(View view, int color) {
        view.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    protected void beforeShow() {
        if (getContext() == null) {
            error("Dialog 未初始化。\n请检查是否在启动对话框前进行初始化操作，使用以下代码进行初始化：\nDialog.init(context)");
        }
        if (style.styleVer != DialogStyle.styleVer) {
            error("Dialog 所引用的 Style 不符合当前适用版本：" + DialogStyle.styleVer + " 引入的 Style(" + style.getClass().getSimpleName() + ") 版本" + style.styleVer);
        }
    }

    protected String getString(int titleResId) {
        if (getContext() == null) {
            error("Dialog 未初始化。\n请检查是否在启动对话框前进行初始化操作，使用以下代码进行初始化：\nDialog.init(context)");
            return null;
        }
        return getContext().getString(titleResId);
    }

    protected int getColor(int backgroundRes) {
        if (getContext() == null) {
            error("Dialog 未初始化。\n请检查是否在启动对话框前进行初始化操作，使用以下代码进行初始化：\nDialog.init(context)");
            return Color.BLACK;
        }
        return getResources().getColor(backgroundRes);
    }

    public enum BOOLEAN {
        TRUE, FALSE
    }

    public abstract String dialogKey();

    protected static void runOnMain(Runnable runnable) {
        if (!HeadDialog.autoRunOnUIThread) runnable.run();
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
