package com.head.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.head.dialog.dialogs.PopTip;
import com.head.titlebar.HeadTitleBar;
import com.head.ui.R;

public class TitleFragment extends Fragment {


    protected View rootView;
    protected HeadTitleBar titlebar1;
    protected HeadTitleBar titlebar2;
    protected HeadTitleBar titlebar3;
    protected HeadTitleBar titlebar4;
    protected HeadTitleBar titlebar5;
    protected HeadTitleBar titlebar6;
    protected HeadTitleBar titlebar7;
    protected HeadTitleBar titlebar8;
    protected HeadTitleBar titlebar9;
    protected HeadTitleBar titlebar10;
    protected HeadTitleBar titlebar11;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        initView(view);
        return view;
    }

    private void initView(View rootView) {
        titlebar1 = (HeadTitleBar) rootView.findViewById(R.id.titlebar1);
        titlebar2 = (HeadTitleBar) rootView.findViewById(R.id.titlebar2);
        titlebar3 = (HeadTitleBar) rootView.findViewById(R.id.titlebar3);
        titlebar4 = (HeadTitleBar) rootView.findViewById(R.id.titlebar4);
        titlebar5 = (HeadTitleBar) rootView.findViewById(R.id.titlebar5);
        titlebar6 = (HeadTitleBar) rootView.findViewById(R.id.titlebar6);
        titlebar7 = (HeadTitleBar) rootView.findViewById(R.id.titlebar7);
        titlebar8 = (HeadTitleBar) rootView.findViewById(R.id.titlebar8);
        titlebar9 = (HeadTitleBar) rootView.findViewById(R.id.titlebar9);
        titlebar10 = (HeadTitleBar) rootView.findViewById(R.id.titlebar10);
        titlebar11 = (HeadTitleBar) rootView.findViewById(R.id.titlebar11);
        {
            titlebar1.setListener(new HeadTitleBar.OnTitleBarListener() {
                @Override
                public void onClicked(View v, int action, String extra) {
                    if (action == HeadTitleBar.ACTION_LEFT_BUTTON
                            || action == HeadTitleBar.ACTION_LEFT_TEXT) {
                        PopTip.show("返回");
                    }

                }
            });
            titlebar2.showCenterProgress();

            titlebar2.setListener(new HeadTitleBar.OnTitleBarListener() {
                @Override
                public void onClicked(View v, int action, String extra) {
                    if (action == HeadTitleBar.ACTION_LEFT_BUTTON
                            || action == HeadTitleBar.ACTION_LEFT_TEXT) {
                        titlebar2.dismissCenterProgress();
                    }

                }
            });
//
            ImageView imageView = titlebar3.getLeftCustomView().findViewById(R.id.selected_search);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopTip.show("自定义标题");
                }
            });

            titlebar4.setListener(new HeadTitleBar.OnTitleBarListener() {
                @Override
                public void onClicked(View v, int action, String extra) {
                    if (action == HeadTitleBar.ACTION_RIGHT_BUTTON
                            || action == HeadTitleBar.ACTION_RIGHT_TEXT) {
                        PopTip.show("返回");
                    }

                }
            });
            titlebar5.showCenterProgress();

            titlebar5.setListener(new HeadTitleBar.OnTitleBarListener() {
                @Override
                public void onClicked(View v, int action, String extra) {
                    if (action == HeadTitleBar.ACTION_RIGHT_BUTTON
                            || action == HeadTitleBar.ACTION_RIGHT_TEXT) {
                        titlebar5.dismissCenterProgress();
                    }

                }
            });
//
            ImageView imageView1 = titlebar6.getRightCustomView().findViewById(R.id.selected_search);
            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopTip.show("自定义标题");
                }
            });


            titlebar10.setListener(new HeadTitleBar.OnTitleBarListener() {
                @Override
                public void onClicked(View v, int action, String extra) {
                    if (action == HeadTitleBar.ACTION_SEARCH_DELETE
                            || action == HeadTitleBar.ACTION_SEARCH_SUBMIT) {
                        PopTip.show("---");
                    }

                }
            });


        }
    }
}
