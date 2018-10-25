package com.example.jd1012.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.jd1012.app.bean.Regin;
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

import com.example.jd1012.mvp.contract.reginContract;


@ActivityScope
public class reginPresenter extends BasePresenter<reginContract.Model, reginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public reginPresenter(reginContract.Model model, reginContract.View rootView) {
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
    @SuppressLint("CheckResult")
    public void regin(String name,String password){
        Observable<Regin> requestregin = mModel.requestregin(name, password);
        requestregin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Regin>() {
                    @Override
                    public void accept(Regin regin) throws Exception {
                        mRootView.data(regin);
                    }
                });

    }
}
