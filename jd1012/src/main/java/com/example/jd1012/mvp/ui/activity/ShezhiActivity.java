package com.example.jd1012.mvp.ui.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.di.component.DaggerShezhiComponent;
import com.example.jd1012.di.module.ShezhiModule;
import com.example.jd1012.mvp.contract.ShezhiContract;
import com.example.jd1012.mvp.presenter.ShezhiPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ShezhiActivity extends BaseActivity<ShezhiPresenter> implements ShezhiContract.View {

    @BindView(R.id.simp_tou)
    SimpleDraweeView simpTou;
    @BindView(R.id.text_data)
    TextView textData;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerShezhiComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .shezhiModule(new ShezhiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shezhi; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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

    @OnClick({R.id.simp_tou, R.id.text_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.simp_tou:
                AlertDialog.Builder builder = new AlertDialog.Builder(ShezhiActivity.this);
                //创建视图
                AlertDialog dialog = builder.create();
                View view1 = View.inflate(ShezhiActivity.this, R.layout.touxiang_item, null);
                dialog.setView(view1);
                Button btn_pai = view1.findViewById(R.id.btn_pai);
                Button btn_bendi = view1.findViewById(R.id.btn_bendi);
                btn_pai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,1);
                    }
                });
                btn_bendi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent,2);
                    }
                });
                dialog.show();
                break;
            case R.id.text_data:
                    new DatePickerDialog(ShezhiActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            textData.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
                        }
                    },2000,1,2).show();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            Bitmap bitmap = data.getParcelableExtra("data");
            simpTou.setImageBitmap(bitmap);
        }else if(requestCode==2&&resultCode==RESULT_OK){
            Uri uri = data.getData();
            simpTou.setImageURI(uri);
        }
    }
}
