package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.FenleiModule;

import com.jess.arms.di.scope.FragmentScope;
import com.example.jd1012.mvp.ui.fragment.FenleiFragment;

@FragmentScope
@Component(modules = FenleiModule.class, dependencies = AppComponent.class)
public interface FenleiComponent {
    void inject(FenleiFragment fragment);
}