package com.petecc.pro.peteccenforcesystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 作者：daiyf on 2017/3/23 19:37
 * 邮箱：misterdai@126.com
 */

public class MyAutoTextAdapter<T> extends BaseAdapter implements Filterable {
    private ArrayFilter mFilter;
    private List<String> names;
    private Context mContext;
    public MyAutoTextAdapter(Context mContext,List<String> names) {
        this.mContext = mContext;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContext);
        textView.setText(names.get(i));
        return textView;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }
    //建立 过滤器
    private class ArrayFilter extends Filter {
        private ArrayList<String> mUnfilteredData;
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            // 过滤后利用FilterResults将过滤结果返回
            FilterResults results = new FilterResults();
            if (mUnfilteredData == null) {
                mUnfilteredData = new ArrayList<String>(names);
            }

            if (prefix == null || prefix.length() == 0) {

                ArrayList<String> list = mUnfilteredData;
                results.values = list; // list是上面的过滤结果
                results.count = list.size();// 结果数量

            } else {
                String prefixString = prefix.toString().toLowerCase();
                ArrayList<String> unfilteredValues = mUnfilteredData;
                int count = unfilteredValues.size();

                ArrayList<String> newValues = new ArrayList<String>(count);

                for (int i = 0; i < count; i++) {
                    String value = mUnfilteredData.get(i);
                    String valueText = value.toLowerCase();

                    if (valueText.contains(prefixString)) { //实现模糊查询
                        //    valueText.contains(prefixString) 源码 ,匹配开头

                        newValues.add(value);
                    }
                    results.values = newValues;
                    results.count = newValues.size();
                }
            }
            return results;
        }

        // 用于更新自动完成列表
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            names = (List<String>) results.values;
            if (results.count > 0 && results != null) {

                notifyDataSetChanged();
            } else {// 无过滤结果，关闭列表
                notifyDataSetInvalidated();
            }

        }
    }
}
