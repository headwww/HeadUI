package com.head.dialog.interfaces;


import com.head.dialog.dialogs.BottomMenu;

/**
*
* 类名称：OnIconChangeCallBack.java <br/>
* 类描述：<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:17 PM <br/>
* @version
*/
public abstract class OnIconChangeCallBack {
    
    private boolean autoTintIconInLightOrDarkMode;
    
    public OnIconChangeCallBack() {
    }
    
    public OnIconChangeCallBack(boolean autoTintIconInLightOrDarkMode) {
        this.autoTintIconInLightOrDarkMode = autoTintIconInLightOrDarkMode;
    }
    
    public abstract int getIcon(BottomMenu bottomMenu, int index, String menuText);
    
    public boolean isAutoTintIconInLightOrDarkMode() {
        return autoTintIconInLightOrDarkMode;
    }
}
