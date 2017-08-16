package com.petecc.pro.peteccenforcesystem.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.petecc.pro.peteccenforcesystem.presenter.Presenter;
import com.petecc.pro.peteccenforcesystem.utils.UIHelper;
import com.petecc.pro.peteccenforcesystem.widget.MainView;

import butterknife.ButterKnife;

/**
 * 作者：daiyf on 2017/3/16 18:02
 * 邮箱：misterdai@126.com
 */

public abstract class BaseFragment extends Fragment implements MainView{
    public Activity mContext;
    public Presenter presenter;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
    }

    public void creatPresenter(){
        presenter = new Presenter(this);
    }

    public void toastShow(int resId) {
        Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null) {
            presenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }


    public void hideLoading() {
        dismissProgressDialog();
    }

    public ProgressDialog progressDialog;

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("加载中");
        progressDialog.show();
    }

    public void showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }

}
