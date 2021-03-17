package com.head.dialog.interfaces;

/**   
*
* 类名称：OnMenuItemClickListener.java <br/>
* 类描述：<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:18 PM <br/>
* @version 
*/
public interface OnMenuItemClickListener<D> {
    boolean onClick(D dialog, CharSequence text, int index);
}
