package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.You;
import com.example.jd1012.app.bean.Zuo;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.FenleiContract;

import io.reactivex.Observable;


@FragmentScope
public class FenleiModel extends BaseModel implements FenleiContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public FenleiModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<Zuo> getdatazuo() {
        Observable<Zuo> zuob = mRepositoryManager.obtainRetrofitService(APIService.class).getZuob();
        return zuob;
    }

    @Override
    public Observable<You> getdatayou(int id) {
        Observable<You> youb = mRepositoryManager.obtainRetrofitService(APIService.class).getYoub(id);
        return youb;
    }

}