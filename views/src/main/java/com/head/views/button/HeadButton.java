package com.head.views.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.appcompat.widget.AppCompatButton;

import com.head.views.R;

/**
 * 类名称：HeadButton.java <br/>
 * 类描述：自定义Button<br/>
 * 创建人：舒文 <br/>
 * 创建时间：3/18/21 10:25 AM <br/>
 */
public class HeadButton extends AppCompatButton {

    //文本颜色
    private int normalTextColor = 0;
    private int pressedTextColor = 0;
    private int unableTextColor = 0;
    private ColorStateList textColorStateList;

    //动画时长
    private int duration = 0;

    //背景颜色
    private int normalBackgroundColor = 0;
    private int pressedBackgroundColor = 0;
    private int unableBackgroundColor = 0;

    private GradientDrawable normalBackground;
    private GradientDrawable pressedBackground;
    private GradientDrawable unableBackground;

    //radius
    private float radius = 0;
    private boolean round;

    //stroke
    private float strokeDashWidth = 0;
    private float strokeDashGap = 0;
    private int normalStrokeWidth = 0;
    private int pressedStrokeWidth = 0;
    private int unableStrokeWidth = 0;
    private int normalStrokeColor = 0;
    private int pressedStrokeColor = 0;
    private int unableStrokeColor = 0;

    private int[][] states;
    private StateListDrawable stateBackground;


    public HeadButton(Context context) {
        this(context, null);
    }

    public HeadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HeadButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        states = new int[4][];
        //pressed, focused, normal, unable
        states[0] = new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[3] = new int[]{-android.R.attr.state_enabled};
        states[2] = new int[]{android.R.attr.state_enabled};

        Drawable drawable = getBackground();
        if (drawable != null && drawable instanceof StateListDrawable) {
            stateBackground = (StateListDrawable) drawable;
        } else {
            stateBackground = new StateListDrawable();
        }

        normalBackground = new GradientDrawable();
        pressedBackground = new GradientDrawable();
        unableBackground = new GradientDrawable();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadButtons);
        try {
            //设置文本
            textColorStateList = getTextColors();
            int defaultNormalTextColor = textColorStateList.getColorForState(states[2], getCurrentTextColor());
            int defaultPressedTextColor = textColorStateList.getColorForState(states[0], getCurrentTextColor());
            int defaultUnableTextColor = textColorStateList.getColorForState(states[3], getCurrentTextColor());
            normalTextColor = typedArray.getColor(R.styleable.HeadButtons_normalTextColor, defaultNormalTextColor);
            pressedTextColor = typedArray.getColor(R.styleable.HeadButtons_pressedTextColor, defaultPressedTextColor);
            unableTextColor = typedArray.getColor(R.styleable.HeadButtons_unableTextColor, defaultUnableTextColor);
            setTextColor();

            //设置动画时间
            duration = typedArray.getInteger(R.styleable.HeadButtons_animationDuration, duration);
            stateBackground.setEnterFadeDuration(duration);
            stateBackground.setExitFadeDuration(duration);

            //设置背景颜色
            normalBackgroundColor = typedArray.getColor(R.styleable.HeadButtons_normalBackgroundColor, 0);
            pressedBackgroundColor = typedArray.getColor(R.styleable.HeadButtons_pressedBackgroundColor, 0);
            unableBackgroundColor = typedArray.getColor(R.styleable.HeadButtons_unableBackgroundColor, 0);
            normalBackground.setColor(normalBackgroundColor);
            pressedBackground.setColor(pressedBackgroundColor);
            unableBackground.setColor(unableBackgroundColor);


            //set radius
            radius = typedArray.getDimensionPixelSize(R.styleable.HeadButtons_radius, 0);
            round = typedArray.getBoolean(R.styleable.HeadButtons_round, false);
            normalBackground.setCornerRadius(radius);
            pressedBackground.setCornerRadius(radius);
            unableBackground.setCornerRadius(radius);

            //set stroke
            strokeDashWidth = typedArray.getDimensionPixelSize(R.styleable.HeadButtons_strokeDashWidth, 0);
            strokeDashGap = typedArray.getDimensionPixelSize(R.styleable.HeadButtons_strokeDashWidth, 0);
            normalStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.HeadButtons_normalStrokeWidth, 0);
            pressedStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.HeadButtons_pressedStrokeWidth, 0);
            unableStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.HeadButtons_unableStrokeWidth, 0);
            normalStrokeColor = typedArray.getColor(R.styleable.HeadButtons_normalStrokeColor, 0);
            pressedStrokeColor = typedArray.getColor(R.styleable.HeadButtons_pressedStrokeColor, 0);
            unableStrokeColor = typedArray.getColor(R.styleable.HeadButtons_unableStrokeColor, 0);
            setStroke();

            //set background
            stateBackground.addState(states[0],pressedBackground);
            stateBackground.addState(states[1], pressedBackground);
            stateBackground.addState(states[3], unableBackground);
            stateBackground.addState(states[2], normalBackground);
            setBackgroundDrawable(stateBackground);

        } finally {
            typedArray.recycle();
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setRound(round);
    }

    private void setStroke(){
        setStroke(normalBackground, normalStrokeColor, normalStrokeWidth);
        setStroke(pressedBackground, pressedStrokeColor, pressedStrokeWidth);
        setStroke(unableBackground, unableStrokeColor, unableStrokeWidth);
    }

    private void setStroke(GradientDrawable background, int strokeColor, int strokeWidth) {
        background.setStroke(strokeWidth, strokeColor, strokeDashWidth, strokeDashGap);
    }


    public void setRound(boolean round){
        this.round = round;
        int height = getMeasuredHeight();
        if(this.round){
            setRadius(height / 2f);
        }
    }
    public void setRadius(@FloatRange(from = 0) float radius) {
        this.radius = radius;
        normalBackground.setCornerRadius(this.radius);
        pressedBackground.setCornerRadius(this.radius);
        unableBackground.setCornerRadius(this.radius);
    }


    private void setTextColor() {
        int[] colors = new int[]{pressedTextColor, pressedTextColor, normalTextColor, unableTextColor};
        textColorStateList = new ColorStateList(states, colors);
        setTextColor(textColorStateList);
    }


    public void setNormalStrokeColor(@ColorInt int normalStrokeColor) {
        this.normalStrokeColor = normalStrokeColor;
        setStroke(normalBackground, this.normalStrokeColor, normalStrokeWidth);
    }

    public void setPressedStrokeColor(@ColorInt int pressedStrokeColor) {
        this.pressedStrokeColor = pressedStrokeColor;
        setStroke(pressedBackground, this.pressedStrokeColor, pressedStrokeWidth);
    }

    public void setUnableStrokeColor(@ColorInt int unableStrokeColor) {
        this.unableStrokeColor = unableStrokeColor;
        setStroke(unableBackground, this.unableStrokeColor, unableStrokeWidth);
    }

    public void setStateStrokeColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable){
        normalStrokeColor = normal;
        pressedStrokeColor = pressed;
        unableStrokeColor = unable;
        setStroke();
    }
    public void setNormalStrokeWidth(int normalStrokeWidth) {
        this.normalStrokeWidth = normalStrokeWidth;
        setStroke(normalBackground, normalStrokeColor, this.normalStrokeWidth);
    }

    public void setPressedStrokeWidth(int pressedStrokeWidth) {
        this.pressedStrokeWidth = pressedStrokeWidth;
        setStroke(pressedBackground, pressedStrokeColor,  this.pressedStrokeWidth);
    }

    public void setUnableStrokeWidth(int unableStrokeWidth) {
        this.unableStrokeWidth = unableStrokeWidth;
        setStroke(unableBackground, unableStrokeColor, this.unableStrokeWidth);
    }

    public void setStateStrokeWidth(int normal, int pressed, int unable){
        normalStrokeWidth = normal;
        pressedStrokeWidth = pressed;
        unableStrokeWidth= unable;
        setStroke();
    }

    public void setStrokeDash(float strokeDashWidth, float strokeDashGap) {
        this.strokeDashWidth = strokeDashWidth;
        this.strokeDashGap = strokeDashGap;
        setStroke();
    }

    public void setStateBackgroundColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int unable){
        normalBackgroundColor = normal;
        pressedBackgroundColor = pressed;
        unableBackgroundColor = unable;
        normalBackground.setColor(normalBackgroundColor);
        pressedBackground.setColor(pressedBackgroundColor);
        unableBackground.setColor(unableBackgroundColor);
    }

    public void setNormalBackgroundColor(@ColorInt int normalBackgroundColor) {
        this.normalBackgroundColor = normalBackgroundColor;
        normalBackground.setColor(this.normalBackgroundColor);
    }

    public void setPressedBackgroundColor(@ColorInt int pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
        pressedBackground.setColor(this.pressedBackgroundColor);
    }

    public void setUnableBackgroundColor(@ColorInt int unableBackgroundColor) {
        this.unableBackgroundColor = unableBackgroundColor;
        unableBackground.setColor(this.unableBackgroundColor);
    }

    public void setAnimationDuration(@IntRange(from = 0)int duration){
        this.duration = duration;
        stateBackground.setEnterFadeDuration(this.duration);
    }

}
