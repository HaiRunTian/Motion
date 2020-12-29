package com.james.motion.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Alan
 * @date: 2020/12/28
 * @time: 22:04
 * @deprecated:
 */
public class ViewPagerAdapter extends FragmentStateAdapter {
    List<Fragment> list;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,  List<Fragment> list) {
        super(fragmentActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
