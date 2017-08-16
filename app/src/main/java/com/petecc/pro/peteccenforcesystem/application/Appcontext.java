package com.petecc.pro.peteccenforcesystem.application;

import android.app.Application;

import com.petecc.pro.peteccenforcesystem.utils.UIHelper;

/**
 * 作者：daiyf on 2017/3/15 16:18
 * 邮箱：misterdai@126.com
 */

public class Appcontext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UIHelper.init(this);
    }
}
