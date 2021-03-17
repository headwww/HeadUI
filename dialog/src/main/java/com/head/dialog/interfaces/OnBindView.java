package com.head.dialog.interfaces;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
*
* 类名称：OnBindView.java <br/>
* 类描述：<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 9:16 PM <br/>
* @version
*/
public abstract class OnBindView<D> {
    int layoutResId;
    View customView;
    
    public OnBindView(int layoutResId) {
        this.layoutResId = layoutResId;
        customView = LayoutInflater.from(BaseDialog.getContext()).inflate(layoutResId, new RelativeLayout(BaseDialog.getContext()),false);
    }
    
    public OnBindView(View customView) {
        this.customView = customView;
    }
    
    public abstract void onBind(D dialog, View v);
    
    public int getLayoutResId() {
        return layoutResId;
    }
    
    public OnBindView<D> setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
        return this;
    }
    
    public View getCustomView() {
        return customView;
    }
    
    public OnBindView<D> setCustomView(View customView) {
        this.customView = customView;
        return this;
    }
    
    public void clean() {
        layoutResId = 0;
        customView = null;
    }
}
