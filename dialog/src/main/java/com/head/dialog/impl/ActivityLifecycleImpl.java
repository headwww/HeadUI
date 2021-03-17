package com.head.dialog.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.head.dialog.interfaces.BaseDialog;

/**
*
* 类名称：ActivityLifecycleImpl.java <br/>
* 类描述：监听当前的activity<br/>
* 创建人：舒文 <br/>
* 创建时间：3/8/21 8:54 PM <br/>
* @version
*/
public class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
    
    private onActivityResumeCallBack onActivityResumeCallBack;
    
    public ActivityLifecycleImpl(ActivityLifecycleImpl.onActivityResumeCallBack onActivityResumeCallBack) {
        this.onActivityResumeCallBack = onActivityResumeCallBack;
    }
    
    public static void init(Context context, ActivityLifecycleImpl.onActivityResumeCallBack onActivityResumeCallBack) {
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new ActivityLifecycleImpl(onActivityResumeCallBack));
    }
    
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        if (onActivityResumeCallBack != null) {
            onActivityResumeCallBack.getActivity(activity);
        }
    }
    
    @Override
    public void onActivityStarted(@NonNull Activity activity) {
    
    }
    
    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        if (activity.isDestroyed() || activity.isFinishing()) {
            return;
        }
        if (onActivityResumeCallBack != null) {
            onActivityResumeCallBack.getActivity(activity);
        }
    }
    
    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    
    }
    
    @Override
    public void onActivityStopped(@NonNull Activity activity) {
    
    }
    
    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
    
    }
    
    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        if (BaseDialog.getContext()==activity){
            BaseDialog.cleanContext();
        }
    }
    
    public interface onActivityResumeCallBack {
        void getActivity(Activity activity);
    }
}
