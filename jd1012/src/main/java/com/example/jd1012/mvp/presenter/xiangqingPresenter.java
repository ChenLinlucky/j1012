package com.example.jd1012.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.jd1012.app.bean.AddShopCartBean;
import com.example.jd1012.app.bean.Xiangqing;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.xiangqingContract;


@ActivityScope
public class xiangqingPresenter extends BasePresenter<xiangqingContract.Model, xiangqingContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public xiangqingPresenter(xiangqingContract.Model model, xiangqingContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    @SuppressLint("ChekcResult")
    public void xiangqing(int pid){
        Observable<Xiangqing> requestxiangqing = mModel.requestxiangqing(pid);
        requestxiangqing.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Xiangqing>() {
                    @Override
                    public void accept(Xiangqing xiangqing) throws Exception {
                        mRootView.data(xiangqing);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void addsp(int uid,int pid){
        Observable<AddShopCartBean> addsp = mModel.addsp(uid, pid);
        addsp.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddShopCartBean>() {
                    @Override
                    public void accept(AddShopCartBean shopCartBean) throws Exception {
                        mRootView.addData(shopCartBean);
                    }
                });
    }
}
