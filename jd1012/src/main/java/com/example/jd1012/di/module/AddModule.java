package com.example.jd1012.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.AddContract;
import com.example.jd1012.mvp.model.AddModel;


@Module
public class AddModule {
    private AddContract.View view;

    /**
     * 构建AddModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddModule(AddContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddContract.View provideAddView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddContract.Model provideAddModel(AddModel model) {
        return model;
    }
}