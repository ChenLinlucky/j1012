package com.example.jd1012.mvp.di.component;

import dagger.Component;

import com.example.jd1012.mvp.ui.fragment.FenleiFragment;
import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.mvp.di.module.FenleiModule;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = FenleiModule.class, dependencies = AppComponent.class)
public interface FenleiComponent {
    void inject(FenleiFragment fragment);
}