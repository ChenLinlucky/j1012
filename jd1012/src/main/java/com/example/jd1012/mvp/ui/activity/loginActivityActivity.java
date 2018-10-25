package com.example.jd1012.mvp.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.Login;
import com.example.jd1012.di.component.DaggerloginActivityComponent;
import com.example.jd1012.di.module.loginActivityModule;
import com.example.jd1012.mvp.contract.loginActivityContract;
import com.example.jd1012.mvp.presenter.loginActivityPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class loginActivityActivity extends BaseActivity<loginActivityPresenter> implements loginActivityContract.View {

    @BindView(R.id.qq_denglu)
    Button qqDenglu;

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regin)
    Button btnRegin;
    @BindView(R.id.edit_user)
    EditText editUser;
    @BindView(R.id.edit_password)
    EditText editPassword;
    private String name;
    private String iconurl;
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(loginActivityActivity.this, "成功了", Toast.LENGTH_LONG).show();
            name = data.get("name");
            iconurl = data.get("iconurl");
            Intent intent2 = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("iconurl", iconurl);
            intent2.putExtras(bundle);
            setResult(888, intent2);
            finish();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(loginActivityActivity.this, "失败：" + t.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(loginActivityActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private String edit_user;
    private String edit_password;
    private SharedPreferences sp;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerloginActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginActivityModule(new loginActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.qq_denglu)
    public void onViewClicked() {
        UMShareAPI.get(this).getPlatformInfo(loginActivityActivity.this, SHARE_MEDIA.QQ, authListener);
    }


    @OnClick({R.id.btn_login, R.id.btn_regin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                edit_user = editUser.getText().toString();
                edit_password = editPassword.getText().toString();
                mPresenter.login(edit_user,edit_password);
                break;
            case R.id.btn_regin:
                Intent intent = new Intent(loginActivityActivity.this, reginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void data(Login login) {

        String code = login.getCode();
        String msg = login.getMsg();
        if (code.equals("0")){
            String mobile = login.getData().getMobile();

            int uid = login.getData().getUid();
            String token = login.getData().getToken();
            sp = getSharedPreferences("flag", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("mobile", mobile);
            editor.putInt("uid", uid);
            editor.putString("token", token);
            editor.commit();


            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("mobile",mobile);
            intent.putExtras(bundle);
            setResult(1,intent);
            finish();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();




    }
}
