package com.example.jd1012.mvp.model.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.mvp.model.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.jd1012.mvp.model.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}