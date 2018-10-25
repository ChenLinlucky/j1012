package com.example.jd1012.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.Regin;
import com.example.jd1012.di.component.DaggerreginComponent;
import com.example.jd1012.di.module.reginModule;
import com.example.jd1012.mvp.contract.reginContract;
import com.example.jd1012.mvp.presenter.reginPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class reginActivity extends BaseActivity<reginPresenter> implements reginContract.View {

    @BindView(R.id.regin_user)
    EditText reginUser;
    @BindView(R.id.regin_password)
    EditText reginPassword;
    @BindView(R.id.regin)
    Button regin;
    private String user;
    private String password;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerreginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .reginModule(new reginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_regin; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.regin)
    public void onViewClicked() {
        user = reginUser.getText().toString();
        password = reginPassword.getText().toString();
        mPresenter.regin(user, password);
    }


    @Override
    public void data(Regin regin) {
        String s1 = regin.getCode();
        String s2 = regin.getMsg();
        Toast.makeText(this, s2, Toast.LENGTH_SHORT).show();
       if(s1.equals("0")){
            Toast.makeText(this, s2, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("user",user);
            bundle.putString("password",password);
            intent.putExtras(bundle);
            setResult(1,intent);
            finish();
        }
    }
}
