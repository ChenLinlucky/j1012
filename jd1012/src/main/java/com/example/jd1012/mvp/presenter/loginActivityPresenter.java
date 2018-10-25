package com.example.jd1012.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.example.jd1012.app.bean.Login;
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

import com.example.jd1012.mvp.contract.loginActivityContract;


@ActivityScope
public class loginActivityPresenter extends BasePresenter<loginActivityContract.Model, loginActivityContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public loginActivityPresenter(loginActivityContract.Model model, loginActivityContract.View rootView) {
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
    public void login(String user,String password){
        Observable<Login> requestlogin = mModel.requestlogin(user, password);
        requestlogin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Login>() {
                    @Override
                    public void accept(Login login) throws Exception {
                        Log.i("aaa",login.getMsg());
                        mRootView.data(login);

                    }
                });
    }

}
