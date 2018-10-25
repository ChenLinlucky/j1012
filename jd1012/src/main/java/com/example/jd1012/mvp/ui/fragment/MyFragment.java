package com.example.jd1012.mvp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.News2;
import com.example.jd1012.di.component.DaggerMyComponent;
import com.example.jd1012.di.module.MyModule;
import com.example.jd1012.mvp.contract.MyContract;
import com.example.jd1012.mvp.presenter.MyPresenter;
import com.example.jd1012.mvp.ui.activity.ShezhiActivity;
import com.example.jd1012.mvp.ui.activity.loginActivityActivity;
import com.example.jd1012.mvp.ui.fragment.adapter.MyAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {


    Unbinder unbinder;
    @BindView(R.id.text_login)
    TextView textLogin;
/*    @BindView(R.id.recyView_jiu)
    RecyclerView recyViewJiu;*/
    @BindView(R.id.simp_login)
    SimpleDraweeView simpLogin;
    @BindView(R.id.img_shezhi)
    ImageView imgShezhi;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMyComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .myModule(new MyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.jiugongge();
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.text_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_login:
                Intent intent = new Intent(getActivity(), loginActivityActivity.class);
                startActivityForResult(intent, 999);
                break;
        }
    }


    @Override
    public void datajiu(News2 news2) {
        List<News2.DataBean> data = news2.getData();
      /*  GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyViewJiu.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter(getActivity(), data);
        recyViewJiu.setAdapter(adapter);*/
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == 1) {
            String mobile = data.getExtras().getString("mobile");
            textLogin.setText(mobile);
        } else {
            if (requestCode == 999 && resultCode == 888) {
                String name = data.getExtras().getString("name");
                String iconurl = data.getExtras().getString("iconurl");
                textLogin.setText(name);

                Uri parse = Uri.parse(iconurl);
                simpLogin.setImageURI(parse);
            }
        }

    }

    @OnClick(R.id.img_shezhi)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), ShezhiActivity.class);
        startActivity(intent);
    }
}
