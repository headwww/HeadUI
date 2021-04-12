package com.head.views.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.head.views.R;

/**
 * 类名称：HeadEditText.java <br/>
 * 类描述：自定义文本框<br/>
 * 创建人：舒文 <br/>
 * 创建时间：3/17/21 5:34 PM <br/>
 */

public class HeadEditText extends AppCompatEditText {


    private OnEditTextClickListener onEditTextClickListener;

    /**
     * 右侧的按钮图片 默认是删除
     */
    private Drawable rightDrawables;
    /**
     * 右侧的按钮图片功能是否显示
     */
    private boolean rightDrawablesVisibility;
    /**
     * 是否可以输入
     */

    private int leftDrawablesID;

    private Drawable leftDrawables;
    private int normalBackgroundColor = 0;


    private boolean isEnabled;
    private boolean focused;
    private boolean isPassword = false;
    private int screenHeight;
    private int screenWidth;
    private GradientDrawable normalBackground;

    public OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private OnClickListener clickListener;


    public HeadEditText(Context context) {
        this(context, null);
    }

    public HeadEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HeadEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadEditTexts);
        normalBackground = new GradientDrawable();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        try {
            //背景颜色
            normalBackgroundColor = typedArray.getResourceId(R.styleable.HeadEditTexts_edBackgroundColor, R.color.default_edit);
            setBackgroundResource(normalBackgroundColor);


            //删除按钮
            int rightDrawablesID = R.drawable.ic_clear;
            rightDrawables = getResources().getDrawable(rightDrawablesID);
            rightDrawables.setBounds(0, 0, 32 * (screenHeight / screenWidth), 32 * (screenHeight / screenWidth));

            //是否是密码框
            isPassword = typedArray.getBoolean(R.styleable.HeadEditTexts_isPassword, false);


            //左边提示按钮
            leftDrawablesID = typedArray.getResourceId(R.styleable.HeadEditTexts_leftDrawables, -1);
            if (leftDrawablesID != -1) {
                leftDrawables = getResources().getDrawable(leftDrawablesID);
                leftDrawables.setBounds(0, 0, 40 * (screenHeight / screenWidth), 40 * (screenHeight / screenWidth));
            }

            //如果是开启密码功能则显示固定的图标
            if (isPassword) {
                setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                leftDrawablesID = R.drawable.ic_visibility_off_black_24dp;
                leftDrawables = getResources().getDrawable(leftDrawablesID);
                leftDrawables.setBounds(0, 0, 40 * (screenHeight / screenWidth), 40 * (screenHeight / screenWidth));
            }

            //是否显示删除功能
            rightDrawablesVisibility = typedArray.getBoolean(R.styleable.HeadEditTexts_rightDrawablesVisibility, true);

            //是否开启文本编辑功能
            isEnabled = typedArray.getBoolean(R.styleable.HeadEditTexts_isEnabled, true);
            if (isEnabled == false) {
                setClickable(true);
                setFocusableInTouchMode(false);
            } else {
                setEnabled(true);
            }

//            setDeleteIconVisible(isEnabled == false && rightDrawablesVisibility && super.getText().toString().length() > 0, leftDrawablesID != -1);
            setDeleteIconVisible(rightDrawablesVisibility && super.getText().toString().length() > 0, leftDrawablesID != -1);

        } finally {
            typedArray.recycle();
        }
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (rightDrawablesVisibility) {
            if (isEnabled) {
                setDeleteIconVisible(hasFocus() && text.length() > 0, leftDrawablesID != -1);
            }else {
                setDeleteIconVisible(  text.length() > 0, leftDrawablesID != -1);

            }
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        this.focused = focused;
        if (isEnabled){
            setDeleteIconVisible(rightDrawablesVisibility && focused && length() > 0, leftDrawablesID != -1);
        }else {
            setDeleteIconVisible(rightDrawablesVisibility && length() > 0, leftDrawablesID != -1);

        }
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                final Drawable drawableRight = rightDrawables;

                if (drawableRight != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawableRight.getBounds().width())) {

                    if (rightDrawablesVisibility && focused) {
                        setText("");
                    }
                    if (rightDrawablesVisibility && isEnabled == false) {
                        setText("");
                    }
                    if (rightDrawablesVisibility && onEditTextClickListener != null) {
                        onEditTextClickListener.right();
                    }

                } else {
                    setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (clickListener != null&&event.getX()<(getWidth() - getPaddingRight() - drawableRight.getBounds().width())) {
                                clickListener.onClick(view);
                            }
                        }
                    });
                }
                Drawable drawableLeft = leftDrawables;
                if (drawableLeft != null && event.getX() <= (getLeft() + drawableLeft.getBounds().width())) {
                    if (onEditTextClickListener != null) {
                        onEditTextClickListener.left();
                    }
                    if (isPassword) {
                        if (leftDrawablesID == R.drawable.ic_visibility_black_24dp) {
                            leftDrawablesID = R.drawable.ic_visibility_off_black_24dp;
                            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            leftDrawablesID = R.drawable.ic_visibility_black_24dp;
                            setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        }
                        leftDrawables = getResources().getDrawable(leftDrawablesID);
                        leftDrawables.setBounds(0, 0, 40 * (screenHeight / screenWidth), 40 * (screenHeight / screenWidth));
                        setSelection(getText().length());
                        if (focused) {
                            setDeleteIconVisible(rightDrawablesVisibility && focused && length() > 0, leftDrawablesID != -1);
                        }
                    }
                }
                invalidate();

                break;
        }
        return super.onTouchEvent(event);
    }

    private void setDeleteIconVisible(boolean right, boolean left) {
        setCompoundDrawablePadding(13);
        setCompoundDrawables(left ? leftDrawables : null,
                null,
                right ? rightDrawables : null,
                null);
        invalidate();
    }

    public boolean isRightDrawablesVisibility() {
        return rightDrawablesVisibility;
    }

    public void setRightDrawablesVisibility(boolean rightDrawablesVisibility) {
        this.rightDrawablesVisibility = rightDrawablesVisibility;
        invalidate();
    }


    public void setOnEditTextClickListener(OnEditTextClickListener onEditTextClickListener) {
        this.onEditTextClickListener = onEditTextClickListener;
    }
}
