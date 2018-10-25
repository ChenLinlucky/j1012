package com.example.jd1012.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.You;
import com.example.jd1012.app.bean.Zuo;
import com.example.jd1012.di.component.DaggerFenleiComponent;
import com.example.jd1012.di.module.FenleiModule;
import com.example.jd1012.mvp.contract.FenleiContract;
import com.example.jd1012.mvp.presenter.FenleiPresenter;
import com.example.jd1012.mvp.ui.fragment.adapter.FenleiAdapter_zuo;
import com.example.jd1012.mvp.ui.fragment.adapter.FenleiAdapter_you;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class FenleiFragment extends BaseFragment<FenleiPresenter> implements FenleiContract.View {

    @BindView(R.id.recyView_zuo)
    RecyclerView recyViewZuo;
    Unbinder unbinder;
    @BindView(R.id.expan_view)
    ExpandableListView expanView;


    public static FenleiFragment newInstance() {
        FenleiFragment fragment = new FenleiFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFenleiComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .fenleiModule(new FenleiModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fenlei, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.zuob();
        mPresenter.youb(1);
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

    //左分类
    @Override
    public void datazuo(Zuo zuo) {
        List<Zuo.DataBean> data = zuo.getData();
        //Toast.makeText(getActivity(), "data:" + data, Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyViewZuo.setLayoutManager(manager);
        FenleiAdapter_zuo adapter_zuo = new FenleiAdapter_zuo(getActivity(),data);
        recyViewZuo.setAdapter(adapter_zuo);
        adapter_zuo.setOnclicklistener(new FenleiAdapter_zuo.onclicklistener() {
            @Override
            public void onclicklistener(View view, int position) {
                int cid = zuo.getData().get(position).getCid();
                mPresenter.youb(cid);
            }
        });

    }

    //右子分类
    @Override
    public void datayou(You you) {
        List<You.DataBean> data = you.getData();
        //Toast.makeText(getActivity(), "data:" + data, Toast.LENGTH_SHORT).show();
        //拿到右边适配器
        FenleiAdapter_you adapter = new FenleiAdapter_you(getActivity(), you);
        expanView.setAdapter(adapter);
        //父级列表默认全部展开
        int groupCount = expanView.getCount();
        for (int i=0; i<groupCount; i++)
        {
            expanView.expandGroup(i);
        }


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
}
