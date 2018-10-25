package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.ShezhiModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.jd1012.mvp.ui.activity.ShezhiActivity;

@ActivityScope
@Component(modules = ShezhiModule.class, dependencies = AppComponent.class)
public interface ShezhiComponent {
    void inject(ShezhiActivity activity);
}