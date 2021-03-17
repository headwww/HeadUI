package com.head.dialog.util;

import android.view.Gravity;

import androidx.annotation.ColorInt;

import com.head.dialog.util.views.wheelview.view.WheelView;

import java.util.Calendar;

/**
 * 类名称：PickerInfo.java <br/>
 * 类描述：选择器设置<br/>
 * 创建人：shuwen <br/>
 * 创建时间：3/8/21 5:01 PM <br/>
 */
public class PickerInfo {
    private int x_offset_year, x_offset_month, x_offset_day, x_offset_hours, x_offset_minutes, x_offset_seconds;//悬浮角度
    private String label_year, label_month, label_day, label_hours, label_minutes, label_seconds;//单位

    private Calendar startDate;               //开始时间
    private Calendar endDate;                 //终止时间

    private int startYear;                    //开始年份
    private int endYear;                      //结尾年份
    private int textSizeContent;              //内容文字大小
    private int textGravity;                  //文本位置
    private int itemsVisibleCount;            //最大可见条目数
    private int dividerColor;                 //分割线的颜色
    private int textColorOut;                 //分割线以外的文字颜色
    private int textColorCenter;              //分割线之间的文字颜色

    private float lineSpacingMultiplier;      // 条目间距倍数 默认1.6

    private WheelView.DividerType dividerType;//分隔线类型

    private boolean isCenterLabel;            //是否只显示中间的label,默认每个item都显示
    private boolean isAlphaGradient;          //透明度渐变
    private boolean cyclic;                   //是否循环
    private boolean isLunarCalendar;          //是否开启农历显示



    public PickerInfo() {
        isLunarCalendar = false;
        textSizeContent = 18;
        textGravity = Gravity.CENTER;
        itemsVisibleCount = 9;
        dividerType = WheelView.DividerType.FILL;
        dividerColor = 0xFFd5d5d5;
        lineSpacingMultiplier = 1.6f;
        textColorOut = 0xFFa8a8a8;
        textColorCenter = 0xFF2a2a2a;
        isCenterLabel = false;
        isAlphaGradient = false;
        cyclic = false;
    }

    public int getX_offset_year() {
        return x_offset_year;
    }

    public int getX_offset_month() {
        return x_offset_month;
    }

    public int getX_offset_day() {
        return x_offset_day;
    }

    public int getX_offset_hours() {
        return x_offset_hours;
    }

    public int getX_offset_minutes() {
        return x_offset_minutes;
    }

    public int getX_offset_seconds() {
        return x_offset_seconds;
    }

    public String getLabel_year() {
        return label_year;
    }

    public String getLabel_month() {
        return label_month;
    }

    public String getLabel_day() {
        return label_day;
    }

    public String getLabel_hours() {
        return label_hours;
    }

    public String getLabel_minutes() {
        return label_minutes;
    }

    public String getLabel_seconds() {
        return label_seconds;
    }



    public Calendar getStartDate() {
        return startDate;
    }

    public PickerInfo setStartDate(Calendar startDate) {
        this.startDate = startDate;
        return this;
    }


    public Calendar getEndDate() {
        return endDate;
    }

    public PickerInfo setEndDate(Calendar endDate) {
        this.endDate = endDate;
        return this;
    }

    public PickerInfo setStartAndEndDate(Calendar startDate, Calendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }


    public int getStartYear() {
        return startYear;
    }

    public PickerInfo setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public PickerInfo setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public PickerInfo setStartAndEndYear(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        return this;
    }

    public int getTextSizeContent() {
        return textSizeContent;
    }

    public PickerInfo setTextSizeContent(int textSizeContent) {
        this.textSizeContent = textSizeContent;
        return this;
    }

    public int getTextGravity() {
        return textGravity;
    }

    public PickerInfo setTextGravity(int textGravity) {
        this.textGravity = textGravity;
        return this;
    }

    public int getItemsVisibleCount() {
        return itemsVisibleCount;
    }

    public PickerInfo setItemsVisibleCount(int itemsVisibleCount) {
        this.itemsVisibleCount = itemsVisibleCount;
        return this;
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public PickerInfo setDividerColor(@ColorInt int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }

    public int getTextColorOut() {
        return textColorOut;
    }

    public PickerInfo setTextColorOut(@ColorInt int textColorOut) {
        this.textColorOut = textColorOut;
        return this;
    }

    public int getTextColorCenter() {
        return textColorCenter;
    }

    public PickerInfo setTextColorCenter(@ColorInt int textColorCenter) {
        this.textColorCenter = textColorCenter;
        return this;
    }

    public float getLineSpacingMultiplier() {
        return lineSpacingMultiplier;
    }

    public PickerInfo setLineSpacingMultiplier(float lineSpacingMultiplier) {
        this.lineSpacingMultiplier = lineSpacingMultiplier;
        return this;
    }

    public WheelView.DividerType getDividerType() {
        return dividerType;
    }

    public PickerInfo setDividerType(WheelView.DividerType dividerType) {
        this.dividerType = dividerType;
        return this;
    }

    public boolean isCenterLabel() {
        return isCenterLabel;
    }

    public PickerInfo setCenterLabel(boolean centerLabel) {
        isCenterLabel = centerLabel;
        return this;
    }

    public boolean isAlphaGradient() {
        return isAlphaGradient;
    }

    public PickerInfo setAlphaGradient(boolean alphaGradient) {
        isAlphaGradient = alphaGradient;
        return this;
    }

    public boolean isCyclic() {
        return cyclic;
    }

    public PickerInfo setCyclic(boolean cyclic) {
        this.cyclic = cyclic;
        return this;
    }

    public boolean isLunarCalendar() {
        return isLunarCalendar;
    }

    public PickerInfo setLunarCalendar(boolean lunarCalendar) {
        isLunarCalendar = lunarCalendar;
        return this;
    }


    public PickerInfo setLabel(String label_year, String label_month, String label_day, String label_hours, String label_mins, String label_seconds) {
        this.label_year = label_year;
        this.label_month = label_month;
        this.label_day = label_day;
        this.label_hours = label_hours;
        this.label_minutes = label_mins;
        this.label_seconds = label_seconds;
        return this;
    }

    /**
     * 设置X轴倾斜角度[ -90 , 90°]
     *
     * @param x_offset_year    年
     * @param x_offset_month   月
     * @param x_offset_day     日
     * @param x_offset_hours   时
     * @param x_offset_minutes 分
     * @param x_offset_seconds 秒
     * @return
     */
    public PickerInfo setTextXOffset(int x_offset_year, int x_offset_month, int x_offset_day,
                                           int x_offset_hours, int x_offset_minutes, int x_offset_seconds) {
        this.x_offset_year = x_offset_year;
        this.x_offset_month = x_offset_month;
        this.x_offset_day = x_offset_day;
        this.x_offset_hours = x_offset_hours;
        this.x_offset_minutes = x_offset_minutes;
        this.x_offset_seconds = x_offset_seconds;
        return this;
    }
}
