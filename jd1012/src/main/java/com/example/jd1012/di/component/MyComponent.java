package com.example.jd1012.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.jd1012.di.module.MyModule;

import com.jess.arms.di.scope.FragmentScope;
import com.example.jd1012.mvp.ui.fragment.MyFragment;

@FragmentScope
@Component(modules = MyModule.class, dependencies = AppComponent.class)
public interface MyComponent {
    void inject(MyFragment fragment);
}