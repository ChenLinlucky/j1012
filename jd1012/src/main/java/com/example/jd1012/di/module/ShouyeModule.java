package com.example.jd1012.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.ShouyeContract;
import com.example.jd1012.mvp.model.ShouyeModel;


@Module
public class ShouyeModule {
    private ShouyeContract.View view;

    /**
     * 构建ShouyeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ShouyeModule(ShouyeContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ShouyeContract.View provideShouyeView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    ShouyeContract.Model provideShouyeModel(ShouyeModel model) {
        return model;
    }
}