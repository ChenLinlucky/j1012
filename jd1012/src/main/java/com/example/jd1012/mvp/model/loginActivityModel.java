package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.Login;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.loginActivityContract;

import io.reactivex.Observable;


@ActivityScope
public class loginActivityModel extends BaseModel implements loginActivityContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public loginActivityModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<Login> requestlogin(String user,String password) {
        Observable<Login> login = mRepositoryManager.obtainRetrofitService(APIService.class).getlogin(user, password);
        return login;
    }
}