package com.head.dialog.interfaces;

import android.view.View;

/**
*
* 类名称：OnInputDialogButtonClickListener.java <br/>
* 类描述：<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:15 PM <br/>
* @version
*/
public interface OnInputDialogButtonClickListener<D extends BaseDialog> extends BaseOnDialogClickCallback{
    
    boolean onClick(D baseDialog, View v, String inputStr);
}
