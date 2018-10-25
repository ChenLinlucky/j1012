package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.mvp.model.api.APIService;
import com.example.jd1012.mvp.model.api.Api;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.ShopContract;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


@FragmentScope
public class ShopModel extends BaseModel implements ShopContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ShopModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<ResponseBody> getdateshop(int uid,String token) {
        Observable<ResponseBody> getshop = mRepositoryManager.obtainRetrofitService(APIService.class).getshop(uid,token);
        return getshop;
    }
}