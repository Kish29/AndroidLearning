package com.kish2.weichat.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kish2.weichat.fragment.MsgContentFragment;

import java.util.ArrayList;
import java.util.List;

/*
 * 消息内容子页面适配器
 */
public class MsgContentFragmentAdapter extends FragmentPagerAdapter {

    private List<String> pageNames;

    public MsgContentFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        pageNames = new ArrayList<>();
    }

    public void setList(List<String> data) {
        pageNames.clear();
        pageNames.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        MsgContentFragment msgContentFragment = new MsgContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("page_name", pageNames.get(position));
        msgContentFragment.setArguments(bundle);
        return msgContentFragment;
    }

    @Override
    public int getCount() {
        return pageNames.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = pageNames.get(position);
        if (plateName == null)
            plateName = "";
        else if (plateName.length() > 15)
            plateName = plateName.substring(0, 15) + "...";
        return plateName;
    }
}
