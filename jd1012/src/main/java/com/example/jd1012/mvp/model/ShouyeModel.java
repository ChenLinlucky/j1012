package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.News;
import com.example.jd1012.app.bean.News2;
import com.example.jd1012.app.bean.News3;
import com.example.jd1012.app.bean.Sousuo;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.ShouyeContract;

import io.reactivex.Observable;


@FragmentScope
public class ShouyeModel extends BaseModel implements ShouyeContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ShouyeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<News> responsedataLunbo() {
        Observable<News> lunbo = mRepositoryManager.obtainRetrofitService(APIService.class).getLunbo();
        return lunbo;
    }

    @Override
    public Observable<News2> responsedatajiugongge() {
        Observable<News2> jiugongge = mRepositoryManager.obtainRetrofitService(APIService.class).getJiugongge();
        return jiugongge;
    }

    @Override
    public Observable<News3> responsedatatuijian() {
        Observable<News3> tuijian = mRepositoryManager.obtainRetrofitService(APIService.class).getTuijian();
        return tuijian;
    }

/*    @Override
    public Observable<Sousuo> responsedataSousuo(String keywords) {
        Observable<Sousuo> shou = mRepositoryManager.obtainRetrofitService(APIService.class).getShou(keywords);
        return shou;
    }*/

}