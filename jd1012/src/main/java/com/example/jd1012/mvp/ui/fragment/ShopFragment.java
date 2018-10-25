package com.example.jd1012.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.Shop;
import com.example.jd1012.app.bean.bean1;
import com.example.jd1012.di.component.DaggerShopComponent;
import com.example.jd1012.di.module.ShopModule;
import com.example.jd1012.mvp.contract.ShopContract;
import com.example.jd1012.mvp.presenter.ShopPresenter;
import com.example.jd1012.mvp.ui.fragment.adapter.OutAdapter;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ShopFragment extends BaseFragment<ShopPresenter> implements ShopContract.View {

    @BindView(R.id.recyView_Shop)
    RecyclerView recyViewShop;
    Unbinder unbinder;
    @BindView(R.id.cb_01)
    CheckBox cb01;
    @BindView(R.id.zj)
    TextView zj;
    private Shop shop;
    private OutAdapter outAdapter;
    private SharedPreferences sp;

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerShopComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .shopModule(new ShopModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 1);
        String token = sp.getString("token", "");
        mPresenter.getShop(uid,token);

        EventBus.getDefault().register(this);//注册
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


    @Override
    public void datashop(String s) {
        Gson gson = new Gson();
        shop = gson.fromJson(s, Shop.class);
        List<Shop.DataBean> data = shop.getData();
        //  Toast.makeText(getActivity(), "data:" + data, Toast.LENGTH_SHORT).show();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyViewShop.setLayoutManager(manager);
        outAdapter = new OutAdapter(getActivity(), data);
        recyViewShop.setAdapter(outAdapter);
        recyViewShop.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));



        //接收回调的 所有复选框
        outAdapter.setOnchangeclicklistener(new OutAdapter.onchangeclicklistener() {
            //里层
            @Override
            public void onchecked(int layoutPosition, boolean checked) {
                //定义一个全局标识
                boolean b=true;
                for (int i = 0; i < shop.getData().size(); i++) {
                    boolean outChecked = shop.getData().get(i).isOutChecked();//外层的状态
                    for (int j = 0; j < shop.getData().get(i).getList().size(); j++) {
                        boolean innerChecked = shop.getData().get(i).getList().get(j).isInnerChecked();//里层的状态
                        b=(b&outChecked&innerChecked);
                    }
                }

                cb01.setChecked(b);
                outAdapter.notifyDataSetChanged();
            }

            //外层
            @Override
            public void onitemchecked(int layoutPosition, boolean checked) {
                shop.getData().get(layoutPosition).setOutChecked(checked);//把默认值改过来

                //定义一个全局标识
                boolean b=true;
                for (int i = 0; i < shop.getData().size(); i++) {
                    boolean outChecked = shop.getData().get(i).isOutChecked();//外层的状态
                    for (int j = 0; j < shop.getData().get(i).getList().size(); j++) {
                        boolean innerChecked = shop.getData().get(i).getList().get(j).isInnerChecked();//里层的状态
                        b=(b&outChecked&innerChecked);
                    }
                }

                cb01.setChecked(b);
                outAdapter.notifyDataSetChanged();
            }
        });
    }


    //全选
    @OnClick(R.id.cb_01)
    public void onViewClicked() {
        if (cb01.isChecked()) {
            for (int i = 0; i < shop.getData().size(); i++) {
                shop.getData().get(i).setOutChecked(true);
                for (int j = 0; j < shop.getData().get(i).getList().size(); j++) {
                    shop.getData().get(i).getList().get(j).setInnerChecked(true);
                }
            }
        } else {
            for (int i = 0; i < shop.getData().size(); i++) {
                shop.getData().get(i).setOutChecked(false);
                for (int j = 0; j < shop.getData().get(i).getList().size(); j++) {
                    shop.getData().get(i).getList().get(j).setInnerChecked(false);
                }
            }
        }



        //总价
        initzong();
        outAdapter.notifyDataSetChanged();
    }


    private void initzong() {
        int zong = 0;
        for (int i = 0; i < shop.getData().size(); i++) {
            for (int j = 0; j < shop.getData().get(i).getList().size(); j++) {
                if(shop.getData().get(i).getList().get(j).isInnerChecked()){
                    zong+=shop.getData().get(i).getList().get(j).getNum()*shop.getData().get(i).getList().get(j).getPrice();
                }
            }
        }
        zj.setText("总价为："+zong+"元");
        outAdapter.notifyDataSetChanged();
    }


  @Subscribe(threadMode = ThreadMode.MAIN)
  public void msg(bean1 bean1){
        initzong();
  }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//销毁
    }

}
