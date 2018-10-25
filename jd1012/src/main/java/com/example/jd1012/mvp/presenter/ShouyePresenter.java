package com.example.jd1012.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.jd1012.app.bean.News;
import com.example.jd1012.app.bean.News2;
import com.example.jd1012.app.bean.News3;
import com.example.jd1012.app.bean.Sousuo;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.ShouyeContract;


@FragmentScope
public class ShouyePresenter extends BasePresenter<ShouyeContract.Model, ShouyeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ShouyePresenter(ShouyeContract.Model model, ShouyeContract.View rootView) {
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
    public void getLunbo(){
        Observable<News> newsObservable = mModel.responsedataLunbo();
        newsObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        mRootView.showdata(news);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
   @SuppressLint("CheckResult")
    public void getjiugongge(){
        Observable<News2> responsedatajiugongge = mModel.responsedatajiugongge();
        responsedatajiugongge.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<News2>() {
                    @Override
                    public void accept(News2 news2) throws Exception {
                        mRootView.showjiu(news2);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    @SuppressLint("CheckResult")
    public void getTuijian(){
        Observable<News3> responsedatatuijian = mModel.responsedatatuijian();
        responsedatatuijian.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<News3>() {
                    @Override
                    public void accept(News3 news3) throws Exception {
                        mRootView.shoutui(news3);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mRootView.shoutui(null);
                    }
                });
    }
 /*   //动态搜索
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
    }*/



}
