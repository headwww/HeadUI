package com.head.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.head.tabbar.HeadTabBar;
import com.head.titlebar.HeadTitleBar;
import com.head.titlebar.utils.KeyboardConflictCompat;
import com.head.ui.fragment.DialogFragment;
import com.head.ui.fragment.FragmentPagerAdapter;
import com.head.ui.fragment.TabFragment;
import com.head.ui.fragment.TitleFragment;
import com.head.ui.fragment.ViewFragment;

import java.util.ArrayList;
import java.util.List;

public class HeadActivity extends AppCompatActivity {

    protected ViewPager viewPager;
    protected HeadTabBar tabBar;
    protected HeadTitleBar title;
    private List<Fragment> fragments = new ArrayList<>();
    private ArrayList<HeadTabBar.Model> models = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_head);
        initView();
        initData();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        KeyboardConflictCompat.assistWindow(getWindow());
    }


    private void initData() {
        final String[] colors = getResources().getStringArray(R.array.default_preview);
        fragments.add(new DialogFragment());
        fragments.add(new TitleFragment());
        fragments.add(new TabFragment());
        fragments.add(new ViewFragment());
        fragments.add(new ViewFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), fragments));

        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_product_gray),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_product_blue))
                        .title("生产")
                        .build()
        );
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_logistics_gray),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_logistics_blue))
                        .title("物流")
                        .build()
        );
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_quality_gray),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_quality_bule))
                        .title("品质")
                        .build()
        );
        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_equipment_gray),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_equipment_blue))
                        .title("设备")
                        .build()
        );

        models.add(
                new HeadTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.tab_my_gray),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.mipmap.tab_my_blue))
                        .title("我的")
                        .build()
        );
        tabBar.setModels(models);
        tabBar.setViewPager(viewPager, 0);
        tabBar.setActiveColor(Color.parseColor("#1296db"));
        tabBar.setAnimationSwitch(false);
        tabBar.setInactiveColor(Color.parseColor("#515151"));
        tabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                title.getCenterTextView().setText(tabBar.getModels().get(position).getTitle());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        tabBar =  findViewById(R.id.tabBar);
        title =  findViewById(R.id.title);
    }
}
