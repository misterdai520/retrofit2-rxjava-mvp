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
import com.petecc.pro.peteccenforcesystem.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 作者：daiyf on 2017/3/19 21:17
 * 邮箱：misterdai@126.com
 * Fragment在ViewPager中进行懒加载：没有滑动到当前页面不进行预加载
 */

public class PageFragment extends BaseFragment {
    @BindView(R.id.fragment_text)
    TextView textView;
    @BindView(R.id.auto_text)
    AutoCompleteTextView autoText;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;



    //是否可见
    private boolean isVisable;
    // 标志位，标志Fragment已经初始化完成。
    private boolean isPrepared = false;
    private boolean getDataSuccess = false;

    /**
     * 实现Fragment数据的缓加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisable = true;
            onVisible();
        } else {
            isVisable = false;
            onInVisible();
        }
    }

    protected void onInVisible() {
        hideLoading();
    }

    protected void onVisible() {
        //加载数据
        loadData();
    }

    private void loadData() {
        if(!isPrepared || !isVisable || getDataSuccess) {
            return;
        }
        UIHelper.showToast("Fragment_Page==="+mPage);
        //// TODO: 2017/8/16
        getData();
    }

    private void getData() {
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
        getDataSuccess = true;
    }

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
        isPrepared = true;
        textView.setText("Fragment #" + mPage);
        loadData();
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

    @Override
    public void creatPresenter() {

    }
}
