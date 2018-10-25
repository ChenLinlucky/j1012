package com.example.jd1012.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.News;
import com.example.jd1012.app.bean.News2;
import com.example.jd1012.app.bean.News3;
import com.example.jd1012.di.component.DaggerShouyeComponent;
import com.example.jd1012.di.module.ShouyeModule;
import com.example.jd1012.mvp.contract.ShouyeContract;
import com.example.jd1012.mvp.presenter.ShouyePresenter;
import com.example.jd1012.mvp.ui.activity.SouSuoActivity;
import com.example.jd1012.mvp.ui.activity.xiangqingActivity;
import com.example.jd1012.mvp.ui.fragment.adapter.MyAdapter;
import com.example.jd1012.mvp.ui.fragment.adapter.MyAdapter_tuijian;
import com.example.jd1012.mvp.ui.widgh.NoticeView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ShouyeFragment extends BaseFragment<ShouyePresenter> implements ShouyeContract.View {

    @BindView(R.id.flybanner)
    FlyBanner flybanner;
    Unbinder unbinder;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.recy_tuijian)
    RecyclerView recyTuijian;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.edit_sou)
    EditText editSou;
    @BindView(R.id.btn_sou)
    Button btnSou;
    @BindView(R.id.notticeView)
    NoticeView notticeView;
    @BindView(R.id.chang)
    TextView mchang;
    @BindView(R.id.data)
    TextView mdata;

    //倒计时
    private int h;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                Calendar instance = Calendar.getInstance();
                int hour = instance.get(instance.HOUR_OF_DAY);
                int tohour = 2;
                if (hour % 2 == 0) {
                    h = hour + tohour;
                } else {
                    h = hour - 1 + tohour;
                }

                int minute = instance.get(Calendar.MINUTE);
                int second = instance.get(Calendar.SECOND);


                //计算时差
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                try {
                    Date d1 = simpleDateFormat.parse(h + ":00:00");
                    Date d2 = simpleDateFormat.parse(hour + ":" + minute + ":" + second);
                    //Date   d2 = new   Date(System.currentTimeMillis());//你也可以获取当前时间
                    long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
                    long hours = (diff / (1000 * 60 * 60));
                    long minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);
                    long seconds = (diff - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;

                    mchang.setText("现在是："+h + "点场！！！");
                    mdata.setText("倒计时："+hours + ":" + minutes + ":" + seconds);

                } catch (Exception e) {
                }

                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    public static ShouyeFragment newInstance() {
        ShouyeFragment fragment = new ShouyeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerShouyeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .shouyeModule(new ShouyeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shouye, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getLunbo();
        mPresenter.getjiugongge();
        mPresenter.getTuijian();
        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金券");
        notticeView.addNotice(notices);
        notticeView.startFlipping();

        //刷新
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore(2000);
            }
        });
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh(2000);
            }
        });
        //设置 Header 为 贝塞尔雷达 样式
        smart.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
//设置 Footer 为 球脉冲 样式
        smart.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));


        //倒计时
        handler.sendEmptyMessageDelayed(0, 1000);
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
    public void showdata(News news) {
        //轮播
        List<News.DataBean> data = news.getData();
        // Toast.makeText(getActivity(), "data::" + data, Toast.LENGTH_SHORT).show();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            list.add(icon);
        }
        flybanner.setImagesUrl(list);


    }

    @Override
    public void showjiu(News2 news2) {

        //九宫格
        List<News2.DataBean> data = news2.getData();
        //Toast.makeText(getActivity(), "news2:" + news2, Toast.LENGTH_SHORT).show();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter(getActivity(), data);

        recyView.setAdapter(adapter);
    }

    //推荐
    @Override
    public void shoutui(News3 news3) {
        List<News3.DataBean.TuijianBean.ListBeanX> list = news3.getData().getTuijian().getList();
        //Toast.makeText(getActivity(), "list:" + list, Toast.LENGTH_SHORT).show();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyTuijian.setLayoutManager(manager);
        MyAdapter_tuijian tuijian = new MyAdapter_tuijian(getActivity(), list);
        tuijian.setOnitemclick(new MyAdapter_tuijian.onitemclick() {
            @Override
            public void onclick(int position) {
                int pid = news3.getData().getTuijian().getList().get(position).getPid();

                Intent intent = new Intent(getActivity(), xiangqingActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });
        recyTuijian.setAdapter(tuijian);
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

    @OnClick(R.id.btn_sou)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SouSuoActivity.class);
        startActivity(intent);
    }


}
