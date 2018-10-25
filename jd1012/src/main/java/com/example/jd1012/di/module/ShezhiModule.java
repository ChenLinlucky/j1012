package com.example.jd1012.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.ShezhiContract;
import com.example.jd1012.mvp.model.ShezhiModel;


@Module
public class ShezhiModule {
    private ShezhiContract.View view;

    /**
     * 构建ShezhiModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ShezhiModule(ShezhiContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ShezhiContract.View provideShezhiView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ShezhiContract.Model provideShezhiModel(ShezhiModel model) {
        return model;
    }
}