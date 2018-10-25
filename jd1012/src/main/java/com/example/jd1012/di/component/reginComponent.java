package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.reginModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.jd1012.mvp.ui.activity.reginActivity;

@ActivityScope
@Component(modules = reginModule.class, dependencies = AppComponent.class)
public interface reginComponent {
    void inject(reginActivity activity);
}