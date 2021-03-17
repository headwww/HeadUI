package com.head.dialog.util;

/**
*
* 类名称：TextInfo.java <br/>
* 类描述：字符的属性<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:03 PM <br/>
* @version
*/
public class TextInfo {
    
    private int fontSize = -1;              //字号大小，值为-1时使用默认样式，单位：dp
    private int gravity = -1;               //对齐方式，值为-1时使用默认样式，取值可使用Gravity.CENTER等对齐方式
    private int fontColor = 1;              //文字颜色，值为1时使用默认样式，取值可以用Color.rgb(r,g,b)等方式获取
    private boolean bold = false;           //是否粗体
    
    public int getFontSize() {
        return fontSize;
    }
    
    public TextInfo setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }
    
    public int getGravity() {
        return gravity;
    }
    
    public TextInfo setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }
    
    public int getFontColor() {
        return fontColor;
    }
    
    public TextInfo setFontColor(int fontColor) {
        this.fontColor = fontColor;
        return this;
    }
    
    public boolean isBold() {
        return bold;
    }
    
    public TextInfo setBold(boolean bold) {
        this.bold = bold;
        return this;
    }
    
    @Override
    public String toString() {
        return "TextInfo{" +
                "fontSize=" + fontSize +
                ", gravity=" + gravity +
                ", fontColor=" + fontColor +
                ", bold=" + bold +
                '}';
    }
}
