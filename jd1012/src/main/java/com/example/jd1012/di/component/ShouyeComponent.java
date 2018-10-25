package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.ShouyeModule;

import com.jess.arms.di.scope.FragmentScope;
import com.example.jd1012.mvp.ui.fragment.ShouyeFragment;

@FragmentScope
@Component(modules = ShouyeModule.class, dependencies = AppComponent.class)
public interface ShouyeComponent {
    void inject(ShouyeFragment fragment);
}