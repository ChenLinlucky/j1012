package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.Regin;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.reginContract;

import io.reactivex.Observable;


@ActivityScope
public class reginModel extends BaseModel implements reginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public reginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

   @Override
    public Observable<Regin> requestregin(String mobile, String password) {
        Observable<Regin> regin = mRepositoryManager.obtainRetrofitService(APIService.class).getregin(mobile, password);
        return regin;
    }
}