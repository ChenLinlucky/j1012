package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.News2;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.MyContract;

import io.reactivex.Observable;


@FragmentScope
public class MyModel extends BaseModel implements MyContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MyModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<News2> requestdatajiu() {
        Observable<News2> jiugongge = mRepositoryManager.obtainRetrofitService(APIService.class).getJiugongge();
        return jiugongge;
    }
}