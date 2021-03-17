package com.head.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.head.tabbar.HeadTabBar;
import com.head.ui.R;

import java.util.ArrayList;

public class TabFragment extends Fragment {


    protected View rootView;
    protected HeadTabBar tab1;
    protected HeadTabBar tab2;
    protected HeadTabBar headSample3;
    protected HeadTabBar headSample5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final ArrayList<HeadTabBar.Model> models = new ArrayList<>();
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_equipment_gray),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_logistics_blue))
                        .title("Heart")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_equipment_gray),
                        Color.parseColor(colors[0]))
//                        .selectedIcon(getResources().getDrawable(R.mipmap.ic_eighth))
                        .title("Cup")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_logistics_blue),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_equipment_gray))
                        .title("Diploma")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_logistics_blue),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.mipmap.ic_eighth))
                        .title("Flag")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_equipment_gray),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_logistics_blue))
                        .title("Medal")
                        .badgeTitle("777")
                        .build()
        );
        tab1.setModels(models);

        tab1.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tab1.getModels().size(); i++) {
                    final HeadTabBar.Model model = tab1.getModels().get(i);
                    tab1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);


        tab2.setModels(models);
        //COORDINATOR布局下收回布局
        tab2.setBehaviorEnabled(true);


        headSample3.setModels(models);



        final ArrayList<HeadTabBar.Model> models5 = new ArrayList<>();
        models5.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_logistics_blue), Color.WHITE
                ).build()
        );
        models5.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_logistics_blue), Color.WHITE
                ).build()
        );
        models5.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_logistics_blue), Color.WHITE
                ).build()
        );
        models5.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_logistics_blue), Color.WHITE
                ).build()
        );
        headSample5.setModels(models5);


    }

    private void initView(View rootView) {
        tab1 = (HeadTabBar) rootView.findViewById(R.id.tab1);
        tab2 = (HeadTabBar) rootView.findViewById(R.id.tab2);
        headSample3 = (HeadTabBar) rootView.findViewById(R.id.head_sample_3);
        headSample5 = (HeadTabBar) rootView.findViewById(R.id.head_sample_5);


    }
}
