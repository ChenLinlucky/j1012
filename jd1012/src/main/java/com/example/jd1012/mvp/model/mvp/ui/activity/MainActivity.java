package com.example.jd1012.mvp.model.mvp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.News;
import com.example.jd1012.mvp.model.di.component.DaggerMainComponent;
import com.example.jd1012.mvp.model.di.module.MainModule;
import com.example.jd1012.mvp.model.mvp.contract.MainContract;
import com.example.jd1012.mvp.model.mvp.presenter.MainPresenter;
import com.example.jd1012.mvp.ui.fragment.FenleiFragment;
import com.example.jd1012.mvp.ui.fragment.MyFragment;
import com.example.jd1012.mvp.ui.fragment.ShopFragment;
import com.example.jd1012.mvp.ui.fragment.ShouyeFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.frag_ment)
    FrameLayout fragMent;
    @BindView(R.id.btn_1)
    RadioButton btn1;
    @BindView(R.id.btn_2)
    RadioButton btn2;
    @BindView(R.id.btn_3)
    RadioButton btn3;
    @BindView(R.id.btn_4)
    RadioButton btn4;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;


    private FragmentManager manager;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        // mPresenter.getLunbo();
        ArrayList<Fragment> list = new ArrayList<>();
        ShouyeFragment shouyeFragment = new ShouyeFragment();
        FenleiFragment fenleiFragment = new FenleiFragment();
        ShopFragment shopFragment = new ShopFragment();
        MyFragment myFragment = new MyFragment();

        list.add(shouyeFragment);
        list.add(fenleiFragment);
        list.add(shopFragment);
        list.add(myFragment);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frag_ment, shouyeFragment).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.btn_1:
                        transaction.replace(R.id.frag_ment, shouyeFragment);
                        break;
                    case R.id.btn_2:
                        transaction.replace(R.id.frag_ment, fenleiFragment);
                        break;
                    case R.id.btn_3:
                        transaction.replace(R.id.frag_ment, shopFragment);
                        break;
                    case R.id.btn_4:
                        transaction.replace(R.id.frag_ment, myFragment);
                        break;
                }
                transaction.commit();
            }

        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


   /*   @Override
      public void showdata(News news) {
          List<News.DataBean> data = news.getData();
          Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        *//* ArrayList<FrameLayout> list = new ArrayList<>();
        list.add(ShouyeFragment);
        list.add(FenleiyeFragment);
        list.add(ShopFragment);
        list.add(MyFragment);*//*
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }



}
