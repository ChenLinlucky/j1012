package com.example.jd1012.mvp.model.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.News;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.model.mvp.contract.MainContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }



}