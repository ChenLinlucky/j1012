package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.AddModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.jd1012.mvp.ui.activity.AddActivity;

@ActivityScope
@Component(modules = AddModule.class, dependencies = AppComponent.class)
public interface AddComponent {
    void inject(AddActivity activity);
}