package com.head.ui;

import android.app.Application;

import com.head.dialog.HeadDialog;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        HeadDialog.init(this);
    }
}
