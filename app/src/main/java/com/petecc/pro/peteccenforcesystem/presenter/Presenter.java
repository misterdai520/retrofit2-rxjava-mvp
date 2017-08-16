package com.petecc.pro.peteccenforcesystem.presenter;

import com.petecc.pro.peteccenforcesystem.base.BasePresenter;
import com.petecc.pro.peteccenforcesystem.model.LoginResult;
import com.petecc.pro.peteccenforcesystem.widget.MainView;
import com.petecc.pro.peteccenforcesystem.model.UserInfoResult;
import com.petecc.pro.peteccenforcesystem.retorfit.ApiCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 作者：daiyf on 2017/3/16 17:18
 * 邮箱：misterdai@126.com
 */

public class Presenter extends BasePresenter<MainView> {

    public Presenter(MainView mvpView) {
        attachView(mvpView);
    }

    /**
     * 登录的方法
     * @param map
     */
    public void login(HashMap<String,String> map) {
        mvpView.showLoading();
        addSubscription(apiStores.login(map), new ApiCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult json) {
                    mvpView.getDataSuccess(json);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

}
