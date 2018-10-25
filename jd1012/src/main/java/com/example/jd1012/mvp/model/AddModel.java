package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.AddShopCartBean;
import com.example.jd1012.mvp.model.api.APIService;
import com.example.jd1012.mvp.model.api.Api;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.AddContract;

import io.reactivex.Observable;


@ActivityScope
public class AddModel extends BaseModel implements AddContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public AddModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<AddShopCartBean> addsp(int uid, int pid) {
       return null;
    }
}