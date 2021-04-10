package com.head.dialog.interfaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.head.dialog.HeadDialog;

import static com.head.dialog.HeadDialog.ERROR_INIT_TIPS;

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
        if (BaseDialog.getContext() == null) {
            HeadDialog.error(ERROR_INIT_TIPS);
            return;
        }
        this.layoutResId = layoutResId;
        customView = LayoutInflater.from(BaseDialog.getContext()).inflate(layoutResId, new RelativeLayout(BaseDialog.getContext()), false);
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

    public OnBindView<D> bindParent(ViewGroup parentView) {
        if (customView == null) return this;
        if (customView.getParent() != null) {
            if (customView.getParent()==parentView){
                return this;
            }
            ((ViewGroup) customView.getParent()).removeView(customView);
        }
        ViewGroup.LayoutParams lp = parentView.getLayoutParams();
        if (lp == null) {
            lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        parentView.addView(customView, lp);
        return this;
    }

    public OnBindView<D> bindParent(ViewGroup parentView, BaseDialog dialog) {
        if (customView == null) return this;
        if (customView.getParent() != null) {
            if (customView.getParent()==parentView){
                return this;
            }
            ((ViewGroup) customView.getParent()).removeView(customView);
        }
        ViewGroup.LayoutParams lp = customView.getLayoutParams();
        if (lp == null) {
            lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        parentView.addView(customView, lp);
        onBind((D) dialog, customView);
        return this;
    }
}
