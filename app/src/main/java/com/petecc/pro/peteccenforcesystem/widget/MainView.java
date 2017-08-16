package com.petecc.pro.peteccenforcesystem.widget;

/**
 * 作者：daiyf on 2017/3/15 16:36
 * 邮箱：misterdai@126.com
 */

public interface  MainView extends BaseView {
    void getDataSuccess(Object model);

    void getDataFail(String msg);
}
