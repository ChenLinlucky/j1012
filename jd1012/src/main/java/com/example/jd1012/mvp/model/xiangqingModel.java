package com.example.jd1012.mvp.model;

import android.app.Application;

import com.example.jd1012.app.bean.AddShopCartBean;
import com.example.jd1012.app.bean.Xiangqing;
import com.example.jd1012.mvp.model.api.APIService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.jd1012.mvp.contract.xiangqingContract;

import io.reactivex.Observable;


@ActivityScope
public class xiangqingModel extends BaseModel implements xiangqingContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public xiangqingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Xiangqing> requestxiangqing(int pid) {
        Observable<Xiangqing> xiangqing = mRepositoryManager.obtainRetrofitService(APIService.class).getxiangqing(pid);
        return xiangqing;
    }

    @Override
    public Observable<AddShopCartBean> addsp(int uid, int pid) {
        Observable<AddShopCartBean> addsp = mRepositoryManager.obtainRetrofitService(APIService.class).addsp(uid, pid);
        return addsp;
    }
}