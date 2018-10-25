package com.example.jd1012.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.loginActivityContract;
import com.example.jd1012.mvp.model.loginActivityModel;


@Module
public class loginActivityModule {
    private loginActivityContract.View view;

    /**
     * 构建loginActivityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public loginActivityModule(loginActivityContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    loginActivityContract.View provideloginActivityView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    loginActivityContract.Model provideloginActivityModel(loginActivityModel model) {
        return model;
    }
}