package com.james.motion.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.james.motion.MyApplication;
import com.james.motion.R;
import com.james.motion.ui.BaseActivity;
import com.james.motion.ui.adapter.ViewPagerAdapter;
import com.james.motion.ui.fragment.ItemFragment;
import com.james.motion.ui.fragment.ItemFragment2;
import com.james.motion.ui.fragment.ScrollingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: Alan
 * @date: 2020/12/27
 * @time: 11:54
 * @deprecated: 主页Activity
 */
public class WorkActivity extends BaseActivity {

    /**
     * 上次点击返回键的时间
     */
    private long lastBackPressed;

    //上次点击返回键的时间
    public static final int QUIT_INTERVAL = 2500;

    @BindView(R.id.reBack)
    RelativeLayout reBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.imgView)
    ImageView imgView;
    @BindView(R.id.reRight)
    RelativeLayout reRight;
    @BindView(R.id.header)
    AppBarLayout header;
    @BindView(R.id.viewpager)
    ViewPager2 viewpager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    List<Fragment> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_work;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        ItemFragment itemFragment = ItemFragment.newInstance(20);
//        ItemFragment2 itemFragment2 = ItemFragment2.newInstance(15);
        ScrollingFragment itemFragment = ScrollingFragment.newInstance("消息");
        ScrollingFragment itemFragment2 = ScrollingFragment.newInstance("工作台");
        ScrollingFragment scrollingFragment = ScrollingFragment.newInstance("朋友圈");
        list.add(itemFragment);
        list.add(itemFragment2);
        list.add(scrollingFragment);

        viewpager.setAdapter(new ViewPagerAdapter(this, list));

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_msg:
                        viewpager.setCurrentItem(0, true);
                        break;
                    case R.id.item_work:
                        viewpager.setCurrentItem(1, true);
                        break;
                    case R.id.item_person:
                        viewpager.setCurrentItem(2, true);
                        break;

                    default:
                        break;

                }
                return true;
            }
        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        });

    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.header, R.id.reRight,R.id.imgView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reRight:
            case R.id.imgView:
                startActivity(new Intent(WorkActivity.this, HomeActivity.class));
                break;
            case R.id.reBack:
                finish();
            default:
                break;

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) { // 表示按返回键 时的操作
                long backPressed = System.currentTimeMillis();
                if (backPressed - lastBackPressed > QUIT_INTERVAL) {
                    lastBackPressed = backPressed;
                    ToastUtils.showShort("再按一次退出");
                    return false;
                } else {
                    moveTaskToBack(false);
                    MyApplication.closeApp(this);
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
