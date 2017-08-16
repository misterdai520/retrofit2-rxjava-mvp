package com.petecc.pro.peteccenforcesystem.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.petecc.pro.peteccenforcesystem.fragment.PageFragment;

/**
 * 作者：daiyf on 2017/3/19 21:13
 * 邮箱：misterdai@126.com
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"基本信息","信用档案","监管信息","商户信息","商品备案","进货台账","销货台账","库存信息"};
    private Context context;
    public ViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return  PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
