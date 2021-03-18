package com.head.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.head.ui.R;
import com.head.views.edittext.HeadEditText;
import com.head.views.edittext.OnEditTextClickListener;

public class ViewFragment extends Fragment {


    protected View rootView;
    protected HeadEditText edit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        initView(view);
        return view;
    }

    private void initView(View rootView) {
        edit = (HeadEditText) rootView.findViewById(R.id.edit);
        edit.setOnEditTextClickListener(new OnEditTextClickListener() {
            @Override
            public void right() {
                Log.e("====","right");
            }

            @Override
            public void left() {
                Log.e("====","left");

            }
        });
    }
}
