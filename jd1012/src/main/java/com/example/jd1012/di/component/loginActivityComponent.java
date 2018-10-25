package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.loginActivityModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.jd1012.mvp.ui.activity.loginActivityActivity;

@ActivityScope
@Component(modules = loginActivityModule.class, dependencies = AppComponent.class)
public interface loginActivityComponent {
    void inject(loginActivityActivity activity);
}