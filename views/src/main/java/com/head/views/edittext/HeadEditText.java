package com.head.views.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

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


    private boolean isEnabled;
    private boolean focused;
    private boolean isPassword = false;
    private int screenHeight;
    private int screenWidth;

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
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        try {
            //删除按钮
            int rightDrawablesID = R.drawable.ic_clear;
            rightDrawables = getResources().getDrawable(rightDrawablesID);
            rightDrawables.setBounds(0, 0, 32 * (screenHeight / screenWidth), 32 * (screenHeight / screenWidth));

            //是否是密码框
            isPassword = typedArray.getBoolean(R.styleable.HeadEditTexts_is_password, false);


            //左边提示按钮
            leftDrawablesID = typedArray.getResourceId(R.styleable.HeadEditTexts_left_drawables, -1);
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
            rightDrawablesVisibility = typedArray.getBoolean(R.styleable.HeadEditTexts_right_drawables_visibility, true);

            //是否开启文本编辑功能
            isEnabled = typedArray.getBoolean(R.styleable.HeadEditTexts_is_enabled, true);
            super.setEnabled(isEnabled);

            setDeleteIconVisible(isEnabled == false && rightDrawablesVisibility && super.getText().toString().length() > 0, leftDrawablesID != -1);

        } finally {
            typedArray.recycle();
        }
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (rightDrawablesVisibility) {
            setDeleteIconVisible(hasFocus() && text.length() > 0, leftDrawablesID != -1);
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        this.focused = focused;
        setDeleteIconVisible(rightDrawablesVisibility && focused && length() > 0, leftDrawablesID != -1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawableRight = rightDrawables;
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
                    invalidate();

                }
                Drawable drawableLeft = leftDrawables;
                if (drawableLeft != null && event.getRawX() <= (getLeft() + drawableLeft.getBounds().width())) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(getResources().getDrawable(R.drawable.editbox_light));
    }

    public void setOnEditTextClickListener(OnEditTextClickListener onEditTextClickListener) {
        this.onEditTextClickListener = onEditTextClickListener;
    }
}
