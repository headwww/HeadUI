package com.head.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.head.ui.R;
import com.head.views.edittext.HeadEditText;
import com.head.views.edittext.OnEditTextClickListener;
import com.head.views.progressbar.HeadProgressBar;

public class ViewFragment extends Fragment {


    protected View rootView;
    protected HeadEditText edit;
    protected HeadProgressBar pb;
    protected HeadProgressBar pb1;
    protected HeadProgressBar pb2;
    protected HeadProgressBar pb3;
    protected HeadProgressBar pb4;
    protected HeadProgressBar pb5;
    protected HeadProgressBar pb6;
    protected TextView tvProgress;
    protected SeekBar seekbar;

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
                Log.e("====", "right");
            }

            @Override
            public void left() {
                Log.e("====", "left");

            }
        });
        pb = (HeadProgressBar) rootView.findViewById(R.id.pb);
        pb1 = (HeadProgressBar) rootView.findViewById(R.id.pb1);
        pb2 = (HeadProgressBar) rootView.findViewById(R.id.pb2);
        pb3 = (HeadProgressBar) rootView.findViewById(R.id.pb3);
        pb4 = (HeadProgressBar) rootView.findViewById(R.id.pb4);
        pb5 = (HeadProgressBar) rootView.findViewById(R.id.pb5);
        pb6 = (HeadProgressBar) rootView.findViewById(R.id.pb6);
        tvProgress = (TextView) rootView.findViewById(R.id.tv_progress);
        seekbar = (SeekBar) rootView.findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                pb.setProgress(progress);
                pb1.setProgress(progress);
                pb1.setSecondProgress(progress - 20);
                pb2.setProgress(progress);
                pb2.setSecondProgress(progress - 30);
                pb3.setProgress(progress);
                pb4.setProgress(progress);
                pb5.setProgress(progress);
                pb6.setProgress(progress);
                if (progress > 80) {
                    pb3.setProgressColor(0xff00ff00);
                    pb5.setGradientColorAndBorderColor(0x7ff5515f, 0x7f9f041b, 0xffff001f);
                } else {
                    pb3.setProgressColor(0xffff0000);
                    pb5.setGradientColorAndBorderColor(0x7fb4ec51, 0x7f429321, 0xff85ff00);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        pb4.setOnProgressChangedListener(new HeadProgressBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(HeadProgressBar progressBar, int max, int progress) {
                tvProgress.setText("prgress = " + progress + ", max = " + max);
            }

            @Override
            public void onSecondProgressChanged(HeadProgressBar progressBar, int max, int progress) {

            }
        });

    }
}
