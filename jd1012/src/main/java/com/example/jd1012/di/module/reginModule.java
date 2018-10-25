package com.example.jd1012.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.reginContract;
import com.example.jd1012.mvp.model.reginModel;


@Module
public class reginModule {
    private reginContract.View view;

    /**
     * 构建reginModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public reginModule(reginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    reginContract.View providereginView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    reginContract.Model providereginModel(reginModel model) {
        return model;
    }
}