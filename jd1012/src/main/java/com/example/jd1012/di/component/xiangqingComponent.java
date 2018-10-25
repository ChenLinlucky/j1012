package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.xiangqingModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.jd1012.mvp.ui.activity.xiangqingActivity;

@ActivityScope
@Component(modules = xiangqingModule.class, dependencies = AppComponent.class)
public interface xiangqingComponent {
    void inject(xiangqingActivity activity);
}