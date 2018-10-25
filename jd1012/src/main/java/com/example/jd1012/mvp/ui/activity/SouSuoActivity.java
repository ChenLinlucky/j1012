package com.example.jd1012.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.Sousuo;
import com.example.jd1012.di.component.DaggerSouSuoComponent;
import com.example.jd1012.di.module.SouSuoModule;
import com.example.jd1012.mvp.contract.SouSuoContract;
import com.example.jd1012.mvp.presenter.SouSuoPresenter;
import com.example.jd1012.mvp.ui.fragment.adapter.SousuoAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SouSuoActivity extends BaseActivity<SouSuoPresenter> implements SouSuoContract.View {


    @BindView(R.id.edit_sou01)
    EditText editSou01;
    @BindView(R.id.btn_sou01)
    Button btnSou01;
    @BindView(R.id.recyView_Sousuo01)
    RecyclerView recyViewSousuo01;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSouSuoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .souSuoModule(new SouSuoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_sou_suo; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
    public void sousuo(Sousuo sousuo) {
        List<Sousuo.DataBean> data = sousuo.getData();
        Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager = new LinearLayoutManager(SouSuoActivity.this, LinearLayoutManager.VERTICAL, false);
        recyViewSousuo01.setLayoutManager(manager);
        SousuoAdapter adapter = new SousuoAdapter(SouSuoActivity.this, data);
        recyViewSousuo01.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sou01)
    public void onViewClicked() {
        String trim = editSou01.getText().toString().trim();
        mPresenter.getsou(trim);
    }
}
