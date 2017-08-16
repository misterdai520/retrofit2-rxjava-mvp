package com.petecc.pro.peteccenforcesystem.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petecc.pro.peteccenforcesystem.R;
import com.petecc.pro.peteccenforcesystem.adapter.MyAutoTextAdapter;
import com.petecc.pro.peteccenforcesystem.base.BaseFragment;
import com.petecc.pro.peteccenforcesystem.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 作者：daiyf on 2017/3/19 21:17
 * 邮箱：misterdai@126.com
 */

public class PageFragment extends BaseFragment {
    @BindView(R.id.fragment_text)
    TextView textView;
    @BindView(R.id.auto_text)
    AutoCompleteTextView autoText;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    /**
     * 初始化Fragment 并设置数据
     * @param page
     * @return
     */
    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    /**
     * 得到数据
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this,view);
        textView.setText("Fragment #" + mPage);
        List<String> items = new ArrayList<>();
        items.add("李四25");
        items.add("李儿四25");
        items.add("李三四25");
        items.add("张三25");
        items.add("李枪25");
        items.add("李五25");
        items.add("张无极25");
        items.add("李蛋25");
        items.add("张三丰25");
        autoText.setAdapter(new MyAutoTextAdapter<String>(this.getActivity(),items));
//        Glide.with(mContext)
//                .load("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=刘诗诗&step_word=&hs=0&pn=7&spn=0&di=197485120580&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=331813745%2C2520303637&os=1834845798%2C2009945128&simid=4112887113%2C541564301&adpicid=0&lpn=0&ln=3924&fr=&fmq=1489936690784_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=star&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201608%2F04%2F20160804051815_Ckr3V.jpeg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B17tpwg2_z%26e3Bv54AzdH3Fks52AzdH3F%3Ft1%3Dm8cm8bn0a&gsm=0&rpstart=0&rpnum=0").into(image);
        return view;
    }

    /**
     * 成功得到数据的处理方法
     * @param model
     */
    @Override
    public void getDataSuccess(Object model) {

    }

    /**
     * 获取数据失败
     * @param msg
     */
    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }
}
