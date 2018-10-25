package com.example.jd1012.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.jd1012.app.bean.Sousuo;
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

import com.example.jd1012.mvp.contract.SouSuoContract;


@ActivityScope
public class SouSuoPresenter extends BasePresenter<SouSuoContract.Model, SouSuoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SouSuoPresenter(SouSuoContract.Model model, SouSuoContract.View rootView) {
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
        //动态搜索
    @SuppressLint("CheckResult")
    public void getsou(String keywords){
        Observable<Sousuo> sousuoObservable = mModel.responsedataSousuo(keywords);
        sousuoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Sousuo>() {
                    @Override
                    public void accept(Sousuo sousuo) throws Exception {
                        mRootView.sousuo(sousuo);
                    }
                });
    }
}
