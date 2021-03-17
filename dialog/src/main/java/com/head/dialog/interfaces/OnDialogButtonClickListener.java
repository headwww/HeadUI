package com.head.dialog.interfaces;

import android.view.View;
/**
*
* 类名称：OnDialogButtonClickListener.java <br/>
* 类描述：<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:16 PM <br/>
* @version
*/
public interface OnDialogButtonClickListener<D extends BaseDialog> extends BaseOnDialogClickCallback{
    
    boolean onClick(D baseDialog, View v);
    
}
