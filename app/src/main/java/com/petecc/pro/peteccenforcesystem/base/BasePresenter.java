package com.petecc.pro.peteccenforcesystem.base;

import com.petecc.pro.peteccenforcesystem.retorfit.ApiStores;
import com.petecc.pro.peteccenforcesystem.retorfit.AppCline;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：daiyf on 2017/3/16 17:05
 * 邮箱：misterdai@126.com
 */

public class BasePresenter<V> {
    public V mvpView;
    protected ApiStores apiStores;
    private CompositeSubscription compositeSubscription;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores = AppCline.getRetrofit().create(ApiStores.class);
    }

    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    private void onUnsubscribe(){
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber){
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
