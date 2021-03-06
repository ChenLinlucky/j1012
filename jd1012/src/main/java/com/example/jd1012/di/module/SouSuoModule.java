package com.example.jd1012.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.SouSuoContract;
import com.example.jd1012.mvp.model.SouSuoModel;


@Module
public class SouSuoModule {
    private SouSuoContract.View view;

    /**
     * 构建SouSuoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SouSuoModule(SouSuoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SouSuoContract.View provideSouSuoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SouSuoContract.Model provideSouSuoModel(SouSuoModel model) {
        return model;
    }
}