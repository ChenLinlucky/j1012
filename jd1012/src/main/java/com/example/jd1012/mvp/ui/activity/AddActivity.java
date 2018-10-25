package com.example.jd1012.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.jd1012.app.bean.AddShopCartBean;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.example.jd1012.di.component.DaggerAddComponent;
import com.example.jd1012.di.module.AddModule;
import com.example.jd1012.mvp.contract.AddContract;
import com.example.jd1012.mvp.presenter.AddPresenter;

import com.example.jd1012.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class AddActivity extends BaseActivity<AddPresenter> implements AddContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addModule(new AddModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_add; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void addData(AddShopCartBean shopCartBean) {

    }
}
