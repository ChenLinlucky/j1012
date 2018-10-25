package com.example.jd1012.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.example.jd1012.mvp.contract.ShopContract;
import com.example.jd1012.mvp.model.ShopModel;


@Module
public class ShopModule {
    private ShopContract.View view;

    /**
     * 构建ShopModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ShopModule(ShopContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ShopContract.View provideShopView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    ShopContract.Model provideShopModel(ShopModel model) {
        return model;
    }
}