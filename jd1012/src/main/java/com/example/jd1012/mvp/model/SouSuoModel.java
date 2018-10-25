package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.Sousuo;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.SouSuoContract;

import io.reactivex.Observable;


@ActivityScope
public class SouSuoModel extends BaseModel implements SouSuoContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SouSuoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Sousuo> responsedataSousuo(String keywords) {
        Observable<Sousuo> shou = mRepositoryManager.obtainRetrofitService(APIService.class).getShou(keywords);
        return shou;
    }
}