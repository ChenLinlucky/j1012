package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.ShopModule;

import com.jess.arms.di.scope.FragmentScope;
import com.example.jd1012.mvp.ui.fragment.ShopFragment;

@FragmentScope
@Component(modules = ShopModule.class, dependencies = AppComponent.class)
public interface ShopComponent {
    void inject(ShopFragment fragment);
}