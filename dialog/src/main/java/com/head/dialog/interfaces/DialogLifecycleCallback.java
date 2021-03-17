package com.head.dialog.interfaces;
/**
*
* 类名称：DialogLifecycleCallback.java <br/>
* 类描述：<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:16 PM <br/>
* @version
*/
public abstract class DialogLifecycleCallback<T extends BaseDialog> {
    
    public void onShow(T dialog){
    
    }
    
    public void onDismiss(T dialog){
    
    }
    
}
