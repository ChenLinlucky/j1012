package com.example.jd1012.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.ShopContract;


@FragmentScope
public class ShopPresenter extends BasePresenter<ShopContract.Model, ShopContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ShopPresenter(ShopContract.Model model, ShopContract.View rootView) {
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
    public void getShop(int uid,String token){
        mModel.getdateshop(uid,token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String s = responseBody.string();
                        mRootView.datashop(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mRootView.datashop(null);
                    }
                });
   }

}
