package com.petecc.pro.peteccenforcesystem.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：daiyf on 2017/3/31 11:15
 * 邮箱：misterdai@126.com
 */

public class Calculator {
    private List<Integer> list = new ArrayList<>();

    public void test() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        int size = list.size();
        long time1 = System.currentTimeMillis();
        for(int i = 0; i < size; i++) {
            LogUtil.d(String.valueOf(list.get(i)));
        }
        long time2 = System.currentTimeMillis();
        LogUtil.d("消耗时间111==="+String.valueOf(time2 - time1));
        long time3 = System.currentTimeMillis();
        for (Integer value  : list) {
            LogUtil.d(String.valueOf(value));
        }
        long time4 = System.currentTimeMillis();
        LogUtil.d("消耗时间111==="+String.valueOf(time4 - time3));
    }

    public double sum(double a, double b){
        return 0;
    }

    public double substract(double a, double b){
        return 0;
    }

    public double divide(double a, double b){
        return 0;
    }

    public double multiply(double a, double b){
        return 0;
    }
}
