package com.example.jd1012.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.FenleiContract;
import com.example.jd1012.mvp.model.FenleiModel;


@Module
public class FenleiModule {
    private FenleiContract.View view;

    /**
     * 构建FenleiModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FenleiModule(FenleiContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    FenleiContract.View provideFenleiView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    FenleiContract.Model provideFenleiModel(FenleiModel model) {
        return model;
    }
}