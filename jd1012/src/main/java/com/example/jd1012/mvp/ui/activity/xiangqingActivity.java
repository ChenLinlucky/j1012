package com.example.jd1012.mvp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.AddShopCartBean;
import com.example.jd1012.app.bean.Xiangqing;
import com.example.jd1012.di.component.DaggerxiangqingComponent;
import com.example.jd1012.di.module.xiangqingModule;
import com.example.jd1012.mvp.contract.xiangqingContract;
import com.example.jd1012.mvp.presenter.xiangqingPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class xiangqingActivity extends BaseActivity<xiangqingPresenter> implements xiangqingContract.View {


    @BindView(R.id.xiangqing_simp)
    SimpleDraweeView xiangqingSimp;
    @BindView(R.id.xiangqing_name)
    TextView xiangqingName;
    @BindView(R.id.xiangqing_price)
    TextView xiangqingPrice;
    @BindView(R.id.text_add)
    TextView textAdd;
    private TextView xiangqing_name;
    private TextView xiangqing_price;
    private SimpleDraweeView xiangqing_simp;
    private SharedPreferences sp;
    private int uid;
    private int pid;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerxiangqingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .xiangqingModule(new xiangqingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_xiangqing; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 1);
        sp = getSharedPreferences("flag", MODE_PRIVATE);
        uid = sp.getInt("uid", 1);
        mPresenter.xiangqing(pid);

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
    public void data(Xiangqing xiangqing) {
        Xiangqing.DataBean data = xiangqing.getData();
        Toast.makeText(this, "data:" + data, Toast.LENGTH_SHORT).show();
        //LayoutInflater.from(xiangqingActivity.this).inflate(R.layout.activity_xiangqing,null);
        xiangqing_name = findViewById(R.id.xiangqing_name);
        xiangqing_price = findViewById(R.id.xiangqing_price);
        xiangqing_simp = findViewById(R.id.xiangqing_simp);
        String[] split = data.getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        xiangqing_simp.setImageURI(uri);
        xiangqing_name.setText(xiangqing.getData().getTitle());
        xiangqing_price.setText(xiangqing.getData().getPrice() + "");


    }

    @Override
    public void addData(AddShopCartBean shopCartBean) {
        String code = shopCartBean.getCode();
        String msg = shopCartBean.getMsg();
        if (code.equals("0")){
            Toast.makeText(this, "加购成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text_add)
    public void onViewClicked() {
        mPresenter.addsp(uid,pid);
    }
}
