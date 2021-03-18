package com.head.ui;

import android.app.Application;

import com.head.dialog.HeadDialog;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        HeadDialog.init(this);

        AutoSizeConfig.getInstance()
                .getUnitsManager()
                .setSupportDP(false)
                .setSupportSubunits(Subunits.MM)
                .setSupportSubunits(Subunits.NONE)
                .setSupportSubunits(Subunits.IN)
                .setSupportSubunits(Subunits.PT);
    }
}
