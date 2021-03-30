package com.head.dialog.dialogs;

import android.animation.Animator;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

import com.head.dialog.HeadDialog;
import com.head.dialog.R;
import com.head.dialog.impl.AnimatorListenerEndCallBack;
import com.head.dialog.interfaces.BaseDialog;
import com.head.dialog.interfaces.DialogConvertViewInterface;
import com.head.dialog.interfaces.DialogLifecycleCallback;
import com.head.dialog.interfaces.OnBackPressedListener;
import com.head.dialog.interfaces.OnBindView;
import com.head.dialog.interfaces.ProgressViewInterface;
import com.head.dialog.util.TextInfo;
import com.head.dialog.util.views.BlurView;
import com.head.dialog.util.views.DialogBaseRelativeLayout;
import com.head.dialog.util.views.MaterialProgressView;
import com.head.dialog.util.views.MaxRelativeLayout;

import java.lang.ref.WeakReference;

/**
 *
 * 类名称：WaitDialog.java <br/>
 * 类描述：等待弹窗<br/>
 * 创建人：舒文 <br/>
 * 创建时间：3/8/21 10:45 PM <br/>
 * @version
 */
public class WaitDialog extends BaseDialog {

    public static BOOLEAN overrideCancelable;
    protected OnBindView<WaitDialog> onBindView;

    public enum TYPE {
        NONE,
        SUCCESS,
        WARNING,
        ERROR
    }

    protected static WeakReference<WaitDialog> me;
    protected CharSequence message;
    protected long tipShowDuration = 1500;
    protected float waitProgress = -1;
    protected int showType = -1;        //-1:Waitdialog 状态标示符，其余为 TipDialog 状态标示
    protected TextInfo messageTextInfo;
    protected int maskColor = -1;
    protected BOOLEAN privateCancelable;

    private DialogLifecycleCallback<WaitDialog> dialogLifecycleCallback;
    protected DialogLifecycleCallback<WaitDialog> tipDialogLifecycleCallback;

    protected WaitDialog() {
        super();
        me = new WeakReference<>(this);
        cancelable = HeadDialog.cancelableTipDialog;
    }

    public static WaitDialog show(CharSequence message) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().message = message;
        me().showType = -1;
        if (dialogImpl != null) {
            dialogImpl.progressView.loading();
            setMessage(message);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.message = message;
            waitDialog.show();
            return waitDialog;
        }
    }

    public static WaitDialog show(Activity activity, CharSequence message) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().message = message;
        me().showType = -1;
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.progressView.loading();
            setMessage(message);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.message = message;
            waitDialog.show(activity);
            return waitDialog;
        }
    }

    public static WaitDialog show(int messageResId) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        me().showType = -1;
        if (dialogImpl != null) {
            dialogImpl.progressView.loading();
            setMessage(messageResId);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.preMessage(messageResId);
            waitDialog.show();
            return waitDialog;
        }
    }

    public static WaitDialog show(Activity activity, int messageResId) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().preMessage(messageResId);
        me().showType = -1;
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            dialogImpl.progressView.loading();
            setMessage(messageResId);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.preMessage(messageResId);
            waitDialog.show(activity);
            return waitDialog;
        }
    }

    public static WaitDialog show(CharSequence message, float progress) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().showType = -1;
        me().preMessage(message);
        if (dialogImpl != null) {
            setMessage(message);
            me().setProgress(progress);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.preMessage(message);
            waitDialog.show();
            waitDialog.setProgress(progress);
            return waitDialog;
        }
    }

    public static WaitDialog show(Activity activity, CharSequence message, float progress) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().showType = -1;
        me().preMessage(message);
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            setMessage(message);
            me().setProgress(progress);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.preMessage(message);
            waitDialog.show(activity);
            waitDialog.setProgress(progress);
            return waitDialog;
        }
    }

    public static WaitDialog show(int messageResId, float progress) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().showType = -1;
        me().preMessage(messageResId);
        if (dialogImpl != null) {
            setMessage(messageResId);
            me().setProgress(progress);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.preMessage(messageResId);
            waitDialog.show();
            waitDialog.setProgress(progress);
            return waitDialog;
        }
    }

    public static WaitDialog show(Activity activity, int messageResId, float progress) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().showType = -1;
        me().preMessage(messageResId);
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            setMessage(messageResId);
            me().setProgress(progress);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.preMessage(messageResId);
            waitDialog.show(activity);
            waitDialog.setProgress(progress);
            return waitDialog;
        }
    }

    public static WaitDialog show(Activity activity, float progress) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().showType = -1;
        if (dialogImpl != null && dialogImpl.bkg.getContext() == activity) {
            me().setProgress(progress);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.show(activity);
            waitDialog.setProgress(progress);
            return waitDialog;
        }
    }

    public static WaitDialog show(float progress) {
        DialogImpl dialogImpl = me().dialogImpl;
        me().showType = -1;
        if (dialogImpl != null) {
            me().setProgress(progress);
            return me();
        } else {
            WaitDialog waitDialog = new WaitDialog();
            waitDialog.show();
            waitDialog.setProgress(progress);
            return waitDialog;
        }
    }

    public float getProgress() {
        return waitProgress;
    }

    public WaitDialog setProgress(float waitProgress) {
        this.waitProgress = waitProgress;
        refreshUI();
        return this;
    }

    private View dialogView;

    public WaitDialog show() {
        super.beforeShow();
        runOnMain(new Runnable() {
            @Override
            public void run() {
                int layoutResId = R.layout.layout_dialog_wait;
                if (style.overrideWaitTipRes() != null && style.overrideWaitTipRes().overrideWaitLayout(isLightTheme()) != 0) {
                    layoutResId = style.overrideWaitTipRes().overrideWaitLayout(isLightTheme());
                }
                dialogView = createView(layoutResId);
                dialogImpl = new DialogImpl(dialogView);
                dialogView.setTag(dialogKey());
                show(dialogView);
            }
        });
        return this;
    }

    public WaitDialog show(final Activity activity) {
        super.beforeShow();
        runOnMain(new Runnable() {
            @Override
            public void run() {
                int layoutResId = R.layout.layout_dialog_wait;
                if (style.overrideWaitTipRes() != null && style.overrideWaitTipRes().overrideWaitLayout(isLightTheme()) != 0) {
                    layoutResId = style.overrideWaitTipRes().overrideWaitLayout(isLightTheme());
                }
                dialogView = createView(layoutResId);
                dialogImpl = new DialogImpl(dialogView);
                dialogView.setTag(dialogKey());
                show(activity, dialogView);
            }
        });

        return this;
    }

    protected DialogImpl dialogImpl;

    public class DialogImpl implements DialogConvertViewInterface {
        public DialogBaseRelativeLayout boxRoot;
        public MaxRelativeLayout bkg;
        public BlurView blurView;
        public RelativeLayout boxProgress;
        public ProgressViewInterface progressView;
        public RelativeLayout boxCustomView;
        public TextView txtInfo;

        public DialogImpl(View convertView) {
            boxRoot = convertView.findViewById(R.id.box_root);
            bkg = convertView.findViewById(R.id.bkg);
            blurView = convertView.findViewById(R.id.blurView);
            boxProgress = convertView.findViewById(R.id.box_progress);
            View progressViewCache = (View) style.overrideWaitTipRes().overrideWaitView(getContext(), isLightTheme());
            if (progressViewCache == null) {
                progressViewCache = new MaterialProgressView(getContext());
            }
            progressView = (ProgressViewInterface) progressViewCache;
            boxProgress.addView(progressViewCache, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            boxCustomView = convertView.findViewById(R.id.box_customView);
            txtInfo = convertView.findViewById(R.id.txt_info);
            init();
            refreshView();
        }

        public void init() {
            if (messageTextInfo == null) messageTextInfo = HeadDialog.tipTextInfo;
            if (backgroundColor == -1) backgroundColor = HeadDialog.tipBackgroundColor;

            blurView.setRadiusPx(dip2px(15));
            boxRoot.setClickable(true);

            boxRoot.setParentDialog(me.get());
            boxRoot.setOnLifecycleCallBack(new DialogBaseRelativeLayout.OnLifecycleCallBack() {
                @Override
                public void onShow() {
                    isShow = true;
                    boxRoot.setAlpha(0f);
                    bkg.post(new Runnable() {
                        @Override
                        public void run() {
                            int enterAnimResId = R.anim.anim_dialog_default_enter;
                            Animation enterAnim = AnimationUtils.loadAnimation(getContext(), enterAnimResId);
                            enterAnim.setInterpolator(new DecelerateInterpolator());
                            if (enterAnimDuration != -1) {
                                enterAnim.setDuration(enterAnimDuration);
                            }
                            bkg.startAnimation(enterAnim);

                            boxRoot.animate()
                                    .setDuration(enterAnimDuration == -1 ? enterAnim.getDuration() : enterAnimDuration)
                                    .alpha(1f)
                                    .setInterpolator(new DecelerateInterpolator())
                                    .setListener(null);

                            getDialogLifecycleCallback().onShow(me());
                        }
                    });

                }

                @Override
                public void onDismiss() {
                    isShow = false;
                    dialogImpl = null;
                    getDialogLifecycleCallback().onDismiss(me());
                    me.clear();
                }
            });

            if (readyTipType != null) {
                progressView.noLoading();
                ((View) progressView).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showTip(readyTipType);
                    }
                }, 100);
            }

            boxRoot.setOnBackPressedListener(new OnBackPressedListener() {
                @Override
                public boolean onBackPressed() {
                    if (onBackPressedListener != null && onBackPressedListener.onBackPressed()) {
                        dismiss();
                        return false;
                    }
                    if (isCancelable()) {
                        dismiss();
                    }
                    return false;
                }
            });
        }

        private float oldProgress;

        public void refreshView() {
            if (style.overrideWaitTipRes() != null) {
                int overrideBackgroundColorRes = style.overrideWaitTipRes().overrideBackgroundColorRes(isLightTheme());
                if (overrideBackgroundColorRes == 0) {
                    overrideBackgroundColorRes = isLightTheme() ? R.color.dialogWaitBkgDark : R.color.dialogWaitBkgLight;
                }
                blurView.setOverlayColor(backgroundColor == -1 ? getResources().getColor(overrideBackgroundColorRes) : backgroundColor);
                int overrideTextColorRes = style.overrideWaitTipRes().overrideTextColorRes(isLightTheme());
                if (overrideTextColorRes == 0) {
                    overrideTextColorRes = isLightTheme() ? R.color.white : R.color.black;
                }
                txtInfo.setTextColor(getResources().getColor(overrideTextColorRes));
                progressView.setColor(getResources().getColor(overrideTextColorRes));
                blurView.setUseBlur(style.overrideWaitTipRes().blurBackground());
            } else {
                if (isLightTheme()) {
                    blurView.setOverlayColor(backgroundColor == -1 ? getResources().getColor(R.color.dialogWaitBkgDark) : backgroundColor);
                    progressView.setColor(Color.WHITE);
                    txtInfo.setTextColor(Color.WHITE);
                } else {
                    blurView.setOverlayColor(backgroundColor == -1 ? getResources().getColor(R.color.dialogWaitBkgLight) : backgroundColor);
                    progressView.setColor(Color.BLACK);
                    txtInfo.setTextColor(Color.BLACK);
                }
            }
            if (HeadDialog.tipProgressColor != -1) progressView.setColor(HeadDialog.tipProgressColor);

            if (waitProgress >= 0 && waitProgress <= 1 && oldProgress != waitProgress) {
                progressView.progress(waitProgress);
                oldProgress = waitProgress;
            }

            showText(txtInfo, message);
            useTextInfo(txtInfo, messageTextInfo);

            if (maskColor != -1) boxRoot.setBackgroundColor(maskColor);


            if (onBindView != null && onBindView.getCustomView() != null) {
                onBindView.bindParent(boxCustomView, me.get());
                boxCustomView.setVisibility(View.VISIBLE);
                boxProgress.setVisibility(View.GONE);
            } else {
                boxCustomView.setVisibility(View.GONE);
                boxProgress.setVisibility(View.VISIBLE);
            }

            //Events
            if (isCancelable()) {
                boxRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doDismiss(v);
                    }
                });
            } else {
                boxRoot.setOnClickListener(null);
            }
        }

        public void doDismiss(final View v) {
            boxRoot.post(new Runnable() {
                @Override
                public void run() {
                    if (v != null) v.setEnabled(false);

                    int exitAnimResId = R.anim.anim_dialog_default_exit;
                    Animation exitAnim = AnimationUtils.loadAnimation(getContext(), exitAnimResId);
                    if (exitAnimDuration != -1) {
                        exitAnim.setDuration(exitAnimDuration);
                    }
                    exitAnim.setInterpolator(new AccelerateInterpolator());
                    bkg.startAnimation(exitAnim);

                    boxRoot.animate()
                            .alpha(0f)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(exitAnimDuration == -1 ? exitAnim.getDuration() : exitAnimDuration)
                            .setListener(new AnimatorListenerEndCallBack() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    dismiss(dialogView);
                                }
                            });
                }
            });
        }



        public void showTip(final TYPE tip) {
            runOnMain(new Runnable() {
                @Override
                public void run() {
                    showType = tip.ordinal();
                    if (progressView == null) return;
                    switch (tip) {
                        case NONE:
                            progressView.loading();
                            return;
                        case SUCCESS:
                            progressView.success();
                            break;
                        case WARNING:
                            progressView.warning();
                            break;
                        case ERROR:
                            progressView.error();
                            break;
                    }

                    //此事件是在完成衔接动画绘制后执行的逻辑
                    progressView.whenShowTick(new Runnable() {
                        @Override
                        public void run() {
                            getDialogLifecycleCallback().onShow(WaitDialog.this);
                            refreshView();
                            ((View) progressView).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (showType > -1) {
                                        doDismiss(null);
                                    }
                                }
                            }, tipShowDuration);
                        }
                    });
                }
            });
        }
    }

    @Override
    public boolean isLightTheme() {
        if (HeadDialog.tipTheme == null) {
            return super.isLightTheme();
        } else {
            return HeadDialog.tipTheme == HeadDialog.THEME.LIGHT;
        }
    }

    @Override
    public String dialogKey() {
        return getClass().getSimpleName() + "(" + Integer.toHexString(hashCode()) + ")";
    }

    public void refreshUI() {
        runOnMain(new Runnable() {
            @Override
            public void run() {
                if (dialogImpl != null) dialogImpl.refreshView();
            }
        });

    }

    public void doDismiss() {
        if (dialogImpl == null) return;
        dialogImpl.doDismiss(null);
    }

    public static void dismiss() {
        me().doDismiss();
    }

    protected static WaitDialog me() {
        if (me == null || me.get() == null) me = new WeakReference<>(new WaitDialog());
        return me.get();
    }


    protected TYPE readyTipType;

    protected void showTip(CharSequence message, TYPE type) {
        showType = type.ordinal();
        this.message = message;
        readyTipType = type;
        show();
    }

    protected void showTip(Activity activity, CharSequence message, TYPE type) {
        showType = type.ordinal();
        this.message = message;
        readyTipType = type;
        show(activity);
    }

    protected void showTip(int messageResId, TYPE type) {
        showType = type.ordinal();
        this.message = getString(messageResId);
        readyTipType = type;
        setDialogLifecycleCallback(tipDialogLifecycleCallback);
        show();
    }

    protected void showTip(Activity activity, int messageResId, TYPE type) {
        showType = type.ordinal();
        this.message = getString(messageResId);
        readyTipType = type;
        setDialogLifecycleCallback(tipDialogLifecycleCallback);
        show(activity);
    }

    public static CharSequence getMessage() {
        return me().message;
    }

    public static WaitDialog setMessage(CharSequence message) {
        me().preMessage(message);
        me().refreshUI();
        return me();
    }

    public static WaitDialog setMessage(int messageResId) {
        me().preMessage(messageResId);
        me().refreshUI();
        return me();
    }

    public boolean isCancelable() {
        if (privateCancelable != null) {
            return privateCancelable == BOOLEAN.TRUE;
        }
        if (overrideCancelable != null) {
            return overrideCancelable == BOOLEAN.TRUE;
        }
        return cancelable;
    }

    public WaitDialog setCancelable(boolean cancelable) {
        this.privateCancelable = cancelable ? BOOLEAN.TRUE : BOOLEAN.FALSE;
        return this;
    }

    /**
     * 用于从 WaitDialog 到 TipDialog 的消息设置
     * 此方法不会立即执行，而是等到动画衔接完成后由事件设置
     *
     * @param message 消息
     * @return me
     */
    protected WaitDialog preMessage(CharSequence message) {
        me().message = message;
        return me();
    }

    protected WaitDialog preMessage(int messageResId) {
        me().message = getString(messageResId);
        return me();
    }

    public DialogLifecycleCallback<WaitDialog> getDialogLifecycleCallback() {
        return dialogLifecycleCallback == null ? new DialogLifecycleCallback<WaitDialog>() {
        } : dialogLifecycleCallback;
    }

    public WaitDialog setDialogLifecycleCallback(DialogLifecycleCallback<WaitDialog> dialogLifecycleCallback) {
        this.dialogLifecycleCallback = dialogLifecycleCallback;
        return this;
    }

    public DialogImpl getDialogImpl() {
        return dialogImpl;
    }

    public WaitDialog setCustomView(OnBindView<WaitDialog> onBindView) {
        this.onBindView = onBindView;
        refreshUI();
        return this;
    }

    public View getCustomView() {
        if (onBindView == null) return null;
        return onBindView.getCustomView();
    }

    public WaitDialog removeCustomView() {
        this.onBindView.clean();
        refreshUI();
        return this;
    }

    public OnBackPressedListener getOnBackPressedListener() {
        return onBackPressedListener;
    }

    public WaitDialog setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
        refreshUI();
        return this;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public WaitDialog setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        refreshUI();
        return this;
    }

    public WaitDialog setBackgroundColorRes(@ColorRes int backgroundColorResId) {
        this.backgroundColor = getColor(backgroundColorResId);
        refreshUI();
        return this;
    }

    public WaitDialog setMaskColor(@ColorInt int maskColor) {
        this.maskColor = maskColor;
        refreshUI();
        return this;
    }

    public DialogLifecycleCallback<WaitDialog> getTipDialogLifecycleCallback() {
        return tipDialogLifecycleCallback == null ? new DialogLifecycleCallback<WaitDialog>() {
        } : tipDialogLifecycleCallback;
    }

    public WaitDialog setTipDialogLifecycleCallback(DialogLifecycleCallback<WaitDialog> dialogLifecycleCallback) {
        this.tipDialogLifecycleCallback = dialogLifecycleCallback;
        return this;
    }

    public WaitDialog setEnterAnimDuration(long enterAnimDuration) {
        this.enterAnimDuration = enterAnimDuration;
        return this;
    }

    public long getExitAnimDuration() {
        return exitAnimDuration;
    }

    public WaitDialog setExitAnimDuration(long exitAnimDuration) {
        this.exitAnimDuration = exitAnimDuration;
        return this;
    }

    @Override
    public void onUIModeChange(Configuration newConfig) {
        refreshUI();
    }
}
