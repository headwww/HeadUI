package com.head.dialog.dialogs;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.head.dialog.HeadDialog;
import com.head.dialog.R;
import com.head.dialog.impl.AnimatorListenerEndCallBack;
import com.head.dialog.interfaces.BaseDialog;
import com.head.dialog.interfaces.DialogConvertViewInterface;
import com.head.dialog.interfaces.DialogLifecycleCallback;
import com.head.dialog.interfaces.OnBackPressedListener;
import com.head.dialog.interfaces.OnDialogButtonClickListener;
import com.head.dialog.interfaces.OnTimeSelectChangeListener;
import com.head.dialog.interfaces.OnTimeSelectListener;
import com.head.dialog.util.PickerInfo;
import com.head.dialog.util.views.BlurView;
import com.head.dialog.util.views.DialogBaseRelativeLayout;
import com.head.dialog.util.views.MaxRelativeLayout;
import com.head.dialog.util.views.WheelTime;
import com.head.dialog.util.views.wheelview.listener.ISelectTimeCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**   
*
* 类名称：TimePickerDialog.java <br/>
* 类描述：时间提醒弹窗，简陋版<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 10:47 PM <br/>
* @version 
*/
public class TimePickerDialog extends BaseDialog {
    public static BOOLEAN overrideCancelable;
    protected TimePickerDialog me = this;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected DialogLifecycleCallback<TimePickerDialog> dialogLifecycleCallback;
    protected OnDialogButtonClickListener<TimePickerDialog> cancelButtonClickListener;
    protected OnDialogButtonClickListener<TimePickerDialog> okButtonClickListener;
    public OnTimeSelectListener timeSelectListener;
    protected BOOLEAN privateCancelable;
    protected CharSequence title;
    protected CharSequence cancelText;
    protected CharSequence okText;

    private View dialogView;
    private DialogImpl dialogImpl;

    private boolean[] timePickerType = new boolean[]{true, true, true, false, false, false};     //显示类型，默认显示： 年月日
    private Calendar date;                    //初始时间


    private PickerInfo timePickerInfo;


    private OnTimeSelectChangeListener onTimeSelectChangeListener;


    public TimePickerDialog() {
        super();
    }

    @Override
    public String dialogKey() {
        return getClass().getSimpleName() + "(" + Integer.toHexString(hashCode()) + ")";
    }

    public static TimePickerDialog show(OnTimeSelectListener timeSelectListener) {
        TimePickerDialog timePickerDialog = new TimePickerDialog( timeSelectListener);
        timePickerDialog.show();
        return timePickerDialog;
    }

    public TimePickerDialog(OnTimeSelectListener timeSelectListener) {
        this.timeSelectListener=timeSelectListener;
    }

    public void show() {
        super.beforeShow();
        runOnMain(new Runnable() {
            @Override
            public void run() {
                int layoutId = isLightTheme() ? R.layout.layout_dialog_time_picker_material : R.layout.layout_dialog_time_picker_material_dark;
                dialogView = createView(layoutId);
                dialogImpl = new DialogImpl(dialogView);
                dialogView.setTag(dialogKey());
                show(dialogView);
            }
        });


    }

    public void show( final Activity activity) {
        super.beforeShow();
        runOnMain(new Runnable() {
            @Override
            public void run() {
                int layoutId = isLightTheme() ? R.layout.layout_dialog_time_picker_material : R.layout.layout_dialog_time_picker_material_dark;
                dialogView = createView(layoutId);
                dialogImpl = new DialogImpl(dialogView);
                dialogView.setTag(dialogKey());
                show(activity, dialogView);
            }
        });


    }

    public void refreshUI() {
        if (getRootFrameLayout() == null) return;
        getRootFrameLayout().post(new Runnable() {
            @Override
            public void run() {
                if (dialogImpl != null) dialogImpl.refreshView();
            }
        });
    }


    public class DialogImpl implements DialogConvertViewInterface {
        public DialogBaseRelativeLayout boxRoot;
        public MaxRelativeLayout bkg;
        public TextView btnCancel;
        public TextView btnSubmit;
        public TextView tvTitle;
        WheelTime wheelTime;

        public DialogImpl(View convertView) {
            boxRoot = convertView.findViewById(R.id.box_root);
            bkg = convertView.findViewById(R.id.bkg);
            btnCancel = convertView.findViewById(R.id.btnCancel);
            btnSubmit = convertView.findViewById(R.id.btnSubmit);
            tvTitle = convertView.findViewById(R.id.tvTitle);
            init();
            refreshView();
        }

        @Override
        public void init() {
            if (timePickerInfo == null) timePickerInfo = HeadDialog.timePickerInfo;

            tvTitle.getPaint().setFakeBoldText(true);
            if (btnCancel != null) btnCancel.getPaint().setFakeBoldText(true);
            if (btnSubmit != null) btnSubmit.getPaint().setFakeBoldText(true);

            boxRoot.setParentDialog(me);
            boxRoot.setOnLifecycleCallBack(new DialogBaseRelativeLayout.OnLifecycleCallBack() {
                private BlurView blurView;

                @Override
                public void onShow() {
                    isShow=true;
                    boxRoot.setAlpha(0f);



                    getDialogLifecycleCallback().onShow(me);
                }

                @Override
                public void onDismiss() {
                    isShow = false;
                    getDialogLifecycleCallback().onDismiss(me);
                }
            });

            //取消按钮
            if (btnCancel != null) {
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cancelButtonClickListener != null) {
                            if (!cancelButtonClickListener.onClick(me, v)) {
                                dismiss();
                            }
                        } else {
                            dismiss();
                        }                    }
                });
            }
            //确定按钮
            if (btnSubmit != null) {
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (okButtonClickListener != null) {
                            if (!okButtonClickListener.onClick(me, v)) {
                                returnData();
                                dismiss();
                            }
                        } else {
                            returnData();
                            dismiss();
                        }
                    }
                });
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


            boxRoot.post(new Runnable() {
                @Override
                public void run() {
                    boxRoot.animate()
                            .setDuration(enterAnimDuration == -1 ? 300 : enterAnimDuration)
                            .alpha(1f)
                            .setInterpolator(new DecelerateInterpolator())
                            .setListener(null);
                    Animation enterAnim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_dialog_bottom_enter);
                    if (enterAnimDuration != -1) {
                        enterAnim.setDuration(enterAnimDuration);
                    }
                    enterAnim.setInterpolator(new DecelerateInterpolator(2f));
                    bkg.startAnimation(enterAnim);

                }
            });


        }

        @Override
        public void refreshView() {
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

            if (timePickerInfo==null)timePickerInfo=new PickerInfo();
            if (theme== HeadDialog.THEME.DARK)timePickerInfo.setTextColorCenter(Color.WHITE);

            wheelTime = new WheelTime(dialogView, timePickerType, timePickerInfo.getTextGravity(), timePickerInfo.getTextSizeContent());
            //是否设置农历
            wheelTime.setLunarMode(timePickerInfo.isLunarCalendar());
            if (timePickerInfo.getStartYear() != 0 && timePickerInfo.getEndYear() != 0
                    && timePickerInfo.getStartYear() <= timePickerInfo.getEndYear()) {
                setRange();
            }

            if (timePickerInfo.getStartDate() != null && timePickerInfo.getEndDate() != null) {
                if (timePickerInfo.getStartDate().getTimeInMillis() > timePickerInfo.getEndDate().getTimeInMillis()) {
                    throw new IllegalArgumentException("startDate can't be later than endDate");
                } else {
                    setRangDate();
                }
            } else if (timePickerInfo.getStartDate() != null) {
                if (timePickerInfo.getStartDate().get(Calendar.YEAR) < 1900) {
                    throw new IllegalArgumentException("The startDate can not as early as 1900");
                } else {
                    setRangDate();

                }
            } else if (timePickerInfo.getEndDate() != null) {
                if (timePickerInfo.getEndDate().get(Calendar.YEAR) > 2100) {
                    throw new IllegalArgumentException("The endDate should not be later than 2100");
                } else {
                    setRangDate();

                }
            } else {
                //没有设置时间范围限制，则会使用默认范围。
                setRangDate();

            }
            int year, month, day, hours, minute, seconds;
            Calendar calendar = Calendar.getInstance();
            if (date == null) {
                calendar.setTimeInMillis(System.currentTimeMillis());
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                hours = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                seconds = calendar.get(Calendar.SECOND);
            } else {
                year = date.get(Calendar.YEAR);
                month = date.get(Calendar.MONTH);
                day = date.get(Calendar.DAY_OF_MONTH);
                hours = date.get(Calendar.HOUR_OF_DAY);
                minute = date.get(Calendar.MINUTE);
                seconds = date.get(Calendar.SECOND);
            }
            wheelTime.setPicker(year, month, day, hours, minute, seconds);
            wheelTime.setSelectChangeCallback(new ISelectTimeCallback() {
                @Override
                public void onTimeSelectChanged() {
                    if (onTimeSelectChangeListener != null) {
                        try {
                            Date date = dateFormat.parse(wheelTime.getTime());
                            onTimeSelectChangeListener.onTimeSelectChanged(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

            });

            wheelTime.setLabels(timePickerInfo.getLabel_year(),
                    timePickerInfo.getLabel_month(),
                    timePickerInfo.getLabel_day(),
                    timePickerInfo.getLabel_hours(),
                    timePickerInfo.getLabel_minutes(),
                    timePickerInfo.getLabel_seconds());
            wheelTime.setTextXOffset(timePickerInfo.getX_offset_year(),
                    timePickerInfo.getX_offset_month(),
                    timePickerInfo.getX_offset_day(),
                    timePickerInfo.getX_offset_hours(),
                    timePickerInfo.getX_offset_minutes(),
                    timePickerInfo.getX_offset_seconds());
            wheelTime.setItemsVisible(timePickerInfo.getItemsVisibleCount());
            wheelTime.setAlphaGradient(timePickerInfo.isAlphaGradient());
            wheelTime.setCyclic(timePickerInfo.isCyclic());
            wheelTime.setDividerColor(timePickerInfo.getDividerColor());
            wheelTime.setDividerType(timePickerInfo.getDividerType());
            wheelTime.setLineSpacingMultiplier(timePickerInfo.getLineSpacingMultiplier());
            wheelTime.setTextColorOut(timePickerInfo.getTextColorOut());
            wheelTime.setTextColorCenter(timePickerInfo.getTextColorCenter());
            wheelTime.isCenterLabel(timePickerInfo.isCenterLabel());

        }

        public void returnData() {
            if (timeSelectListener != null) {
                try {
                    Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
                    timeSelectListener.onTimeSelect(date, getRootFrameLayout());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 设置可以选择的时间范围, 要在setTime之前调用才有效果
         */
        private void setRange() {
            wheelTime.setStartYear(timePickerInfo.getStartYear());
            wheelTime.setEndYear(timePickerInfo.getEndYear());
        }

        /**
         * 设置可以选择的时间范围, 要在setTime之前调用才有效果
         */
        private void setRangDate() {
            wheelTime.setRangDate(timePickerInfo.getStartDate(), timePickerInfo.getEndDate());
            //如果手动设置了时间范围
            if (timePickerInfo.getStartDate() != null && timePickerInfo.getEndDate() != null) {
                //若默认时间未设置，或者设置的默认时间越界了，则设置默认选中时间为开始时间。
                if (date == null || date.getTimeInMillis() < timePickerInfo.getStartDate().getTimeInMillis()
                        || date.getTimeInMillis() > timePickerInfo.getEndDate().getTimeInMillis()) {
                    date = timePickerInfo.getStartDate();
                }
            } else if (timePickerInfo.getStartDate() != null) {
                //没有设置默认选中时间,那就拿开始时间当默认时间
                date = timePickerInfo.getStartDate();
            } else if (timePickerInfo.getEndDate() != null) {
                date = timePickerInfo.getEndDate();
            }
        }


        @Override
        public void doDismiss(View v) {
            if (v != null) v.setEnabled(false);

            
            ObjectAnimator exitAnim = ObjectAnimator.ofFloat(bkg, "y", bkg.getY(), boxRoot.getHeight());
            exitAnim.setDuration(exitAnimDuration == -1 ? 300 : exitAnimDuration);
            exitAnim.start();

            boxRoot.animate()
                    .alpha(0f)
                    .setInterpolator(new AccelerateInterpolator())
                    .setDuration(exitAnim.getDuration())
                    .setListener(new AnimatorListenerEndCallBack() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            dismiss(dialogView);
                        }
                    });
        }
    }


    public void dismiss() {
        if (dialogImpl == null) return;
        dialogImpl.doDismiss(null);
    }

    @Override
    public void onUIModeChange(Configuration newConfig) {
        if (dialogView != null) {
            dismiss(dialogView);
        }
        int layoutId = isLightTheme() ? R.layout.layout_dialog_time_picker_material : R.layout.layout_dialog_time_picker_material_dark;
        enterAnimDuration = 0;
        dialogView = createView(layoutId);
        dialogImpl = new DialogImpl(dialogView);
        dialogView.setTag(getClass().getSimpleName() + "(" + Integer.toHexString(hashCode()) + ")");
        show(dialogView);
    }


    public TimePickerDialog setTimePickerType(boolean[] timePickerType) {
        this.timePickerType = timePickerType;
        refreshUI();
        return this;
    }

    public PickerInfo getTimePickerInfo() {
        return timePickerInfo;
    }

    public TimePickerDialog setTimePickerInfo(PickerInfo timePickerInfo) {
        this.timePickerInfo = timePickerInfo;
        refreshUI();
        return this;
    }

    public OnTimeSelectChangeListener getOnTimeSelectChangeListener() {
        return onTimeSelectChangeListener;
    }

    public TimePickerDialog setOnTimeSelectChangeListener(OnTimeSelectChangeListener onTimeSelectChangeListener) {
        this.onTimeSelectChangeListener = onTimeSelectChangeListener;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    /**
     * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
     *
     * @param date
     * @return TimePickerDialog
     */
    public TimePickerDialog setDate(Calendar date) {
        this.date = date;
        refreshUI();
        return this;
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

    public TimePickerDialog setCancelable(boolean cancelable) {
        this.privateCancelable = cancelable ? BOOLEAN.TRUE : BOOLEAN.FALSE;
        refreshUI();
        return this;
    }


    public DialogLifecycleCallback<TimePickerDialog> getDialogLifecycleCallback() {
        return dialogLifecycleCallback == null ? new DialogLifecycleCallback<TimePickerDialog>() {
        } : dialogLifecycleCallback;
    }

    public TimePickerDialog setDialogLifecycleCallback(DialogLifecycleCallback<TimePickerDialog> dialogLifecycleCallback) {
        this.dialogLifecycleCallback = dialogLifecycleCallback;
        return this;
    }

    public TimePickerDialog setOkButton(OnDialogButtonClickListener<TimePickerDialog> OkButtonClickListener) {
        this.okButtonClickListener = OkButtonClickListener;
        return this;
    }

    public TimePickerDialog setOkButton(CharSequence OkText, OnDialogButtonClickListener<TimePickerDialog> OkButtonClickListener) {
        this.okText = OkText;
        this.okButtonClickListener = OkButtonClickListener;
        return this;
    }

    public TimePickerDialog setOkButton(int OkTextResId, OnDialogButtonClickListener<TimePickerDialog> OkButtonClickListener) {
        this.okText = getString(OkTextResId);
        this.okButtonClickListener = OkButtonClickListener;
        return this;
    }
    
    
    public TimePickerDialog setCancelButton(OnDialogButtonClickListener<TimePickerDialog> cancelButtonClickListener) {
        this.cancelButtonClickListener = cancelButtonClickListener;
        return this;
    }

    public TimePickerDialog setCancelButton(CharSequence cancelText, OnDialogButtonClickListener<TimePickerDialog> cancelButtonClickListener) {
        this.cancelText = cancelText;
        this.cancelButtonClickListener = cancelButtonClickListener;
        return this;
    }

    public TimePickerDialog setCancelButton(int cancelTextResId, OnDialogButtonClickListener<TimePickerDialog> cancelButtonClickListener) {
        this.cancelText = getString(cancelTextResId);
        this.cancelButtonClickListener = cancelButtonClickListener;
        return this;
    }

    public OnDialogButtonClickListener<TimePickerDialog> getCancelButtonClickListener() {
        return cancelButtonClickListener;
    }

    public TimePickerDialog setCancelButtonClickListener(OnDialogButtonClickListener<TimePickerDialog> cancelButtonClickListener) {
        this.cancelButtonClickListener = cancelButtonClickListener;
        refreshUI();
        return this;
    }

    public OnBackPressedListener getOnBackPressedListener() {
        return onBackPressedListener;
    }

    public TimePickerDialog setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
        refreshUI();
        return this;
    }

}
