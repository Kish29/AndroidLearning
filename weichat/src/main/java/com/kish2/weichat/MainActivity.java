package com.kish2.weichat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.kish2.weichat.adapter.MainFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /*
     * 菜单标题
     */
    private final int[] TAB_TITLES = new int[]{
            R.string.menu_msg,
            R.string.menu_contact,
            R.string.menu_find,
            R.string.menu_me
    };

    /*
     * 菜单图标
     */
    private final int[] TAB_IMG = new int[]{
            R.drawable.tab_main_msg_selector,
            R.drawable.tab_main_contact_selector,
            R.drawable.tab_main_find_selector,
            R.drawable.tab_main_me_selector
    };

    // 包内可见ViewPage
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    // 包内可见TabLayout
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    /*
     * 页卡适配器
     */
    private PagerAdapter pagerAdapter;

    /*
     * 退出时间
     */
    private long exitTime;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 绑定 MainActivity
        ButterKnife.bind(this);

        // 初始化页卡
        initPager();

        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMG);

        Window window = getWindow();
        window.setStatusBarColor(android.R.color.transparent);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    /*
     * 设置页卡效果
     */
    @SuppressLint("InflateParams")
    private void setTabs(TabLayout tabs, LayoutInflater inflater, int[] tabTitles, int[] tabImg) {
        int len = tabImg.length;
        if (tabTitles.length != tabImg.length)
            return;
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            TabLayout.Tab tab = tabs.newTab();
            // 该视图是微信页面展示的各个页面
            View view = inflater.inflate(R.layout.item_main_menu, null);

            // 使用自定义视图，目的是为了便于修改
            tab.setCustomView(view);

            // 设置各个页面的标签图片和文本
            ImageView imgTab = view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImg[i]);
            TextView tabTitle = view.findViewById(R.id.txt_tab);
            tabTitle.setText(tabTitles[i]);

            tabLayout.addTab(tab);

        }
    }

    // 初始化页卡函数
    private void initPager() {
        // 初始化数据类
        pagerAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // 关联切换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));      // 设置首页的page切换
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            // 重写键盘事件分发，onKeyDown方法某些情况下捕获不到，只能在这里写
            if (System.currentTimeMillis() - exitTime > 2000) {
                Snackbar snackbar = Snackbar.make(viewPager, "再按一次退出程序", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundResource(R.color.colorPrimary);
                snackbar.show();
                exitTime = System.currentTimeMillis();
            } else finish();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}




































































































