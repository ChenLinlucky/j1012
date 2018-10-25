package com.example.jd1012.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.xiangqingContract;
import com.example.jd1012.mvp.model.xiangqingModel;


@Module
public class xiangqingModule {
    private xiangqingContract.View view;

    /**
     * 构建xiangqingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public xiangqingModule(xiangqingContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    xiangqingContract.View providexiangqingView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    xiangqingContract.Model providexiangqingModel(xiangqingModel model) {
        return model;
    }
}