package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.SouSuoModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.jd1012.mvp.ui.activity.SouSuoActivity;

@ActivityScope
@Component(modules = SouSuoModule.class, dependencies = AppComponent.class)
public interface SouSuoComponent {
    void inject(SouSuoActivity activity);
}