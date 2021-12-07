package com.head.views.spinner;

import android.content.Context;
import android.widget.ListAdapter;

public class HeadSpinnerAdapterWrapper extends HeadSpinnerBaseAdapter {

    private final ListAdapter baseAdapter;

    HeadSpinnerAdapterWrapper(
            Context context,
            ListAdapter toWrap,
            int textColor, float textSize,

            int backgroundSelector,
            SpinnerTextFormatter spinnerTextFormatter,
            PopUpTextAlignment horizontalAlignment
    ) {
        super(context, textColor,textSize, backgroundSelector, spinnerTextFormatter, horizontalAlignment);
        baseAdapter = toWrap;
    }

    @Override
    public int getCount() {
        return baseAdapter.getCount() - 1;
    }

    @Override
    public Object getItem(int position) {
        return baseAdapter.getItem(position >= selectedIndex ? position + 1 : position);
    }

    @Override
    public Object getItemInDataset(int position) {
        return baseAdapter.getItem(position);
    }
}