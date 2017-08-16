package com.petecc.pro.peteccenforcesystem.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.petecc.pro.peteccenforcesystem.R;
import com.petecc.pro.peteccenforcesystem.presenter.Presenter;
import com.petecc.pro.peteccenforcesystem.utils.UIHelper;
import com.petecc.pro.peteccenforcesystem.widget.MainView;

/**
 * 作者：daiyf on 2017/3/16 17:27
 * 邮箱：misterdai@126.com
 */

public abstract class BaseActivity extends AppCompatActivity implements MainView {
    public Presenter presenter;
    public Activity mContext;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mContext = this;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mContext = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mContext = this;
    }

    public void creatPresenter(){
        presenter = new Presenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null) {
            presenter.detachView();
        }
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

    public void toastShow(int resId) {
        UIHelper.showToast(resId);
    }

    public void toastShow(String resId) {
        UIHelper.showToast(resId);
    }


    public void showLoading() {
        showProgressDialog();
    }


    public void hideLoading() {
        dismissProgressDialog();
    }
}
