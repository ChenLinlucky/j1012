package com.example.jd1012.mvp.ui.fragment.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.Shop;
import com.example.jd1012.app.bean.bean1;
import com.facebook.drawee.view.SimpleDraweeView;
import com.example.jd1012.mvp.ui.widgh.toggbutton;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.innerHolde>{
    private Context context;
    private List<Shop.DataBean.ListBean> list;
    private TextView text_num;

    public InnerAdapter(Context context, List<Shop.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public innerHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inner_item, null);
        innerHolde holde = new innerHolde(view);
        return holde;
    }

    @Override
    public void onBindViewHolder(@NonNull innerHolde holder, int position) {
        holder.cb_03.setChecked(list.get(position).isInnerChecked());

        holder.shop_name.setText(list.get(position).getTitle());
        holder.shop_price.setText(list.get(position).getPrice()+"元");
        String[] split = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.shop_simp.setImageURI(uri);
        text_num = holder.toggbutton.findViewById(R.id.text_num);
        text_num.setText(list.get(position).getNum()+"");



        //选中里层条目控制商家
        holder.cb_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setInnerChecked(holder.cb_03.isChecked());
                onchangelistener.onchecked(holder.getLayoutPosition(),holder.cb_03.isChecked());
            }
        });


        //加减器
        holder.toggbutton.setAddAndMinus(new toggbutton.AddAndMinus() {
            @Override
            public void add() {
                list.get(position).setNum(list.get(position).getNum()+1);
                bean1 be = new bean1();
                EventBus.getDefault().post(be);
            }

            @Override
            public void munus() {
                list.get(position).setNum(list.get(position).getNum()-1);
                bean1 bean1 = new bean1();
                EventBus.getDefault().post(bean1);//发送普通事件
            }
        });


        //子条目的选中状态以及下标
        holder.cb_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setInnerChecked(holder.cb_03.isChecked());//判断
                //通过 获取下标  接口来回调给外部
                onchangelistener.onchecked(holder.getLayoutPosition(),holder.cb_03.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class innerHolde extends RecyclerView.ViewHolder{

        private final TextView shop_name;
        private final TextView shop_price;
        private final SimpleDraweeView shop_simp;
        private final CheckBox cb_03;
        private toggbutton toggbutton;


        public innerHolde(View itemView) {
            super(itemView);
            shop_name = itemView.findViewById(R.id.shop_name);
            shop_price = itemView.findViewById(R.id.shop_price);
            shop_simp = itemView.findViewById(R.id.shop_simp);
            cb_03 = itemView.findViewById(R.id.cb_03);
            toggbutton = itemView.findViewById(R.id.toggbutton);
        }
    }


    //定义接口回调
    private onchangelistener onchangelistener;
    public interface onchangelistener{
        void onchecked(int layoutPosition, boolean checked);
    }
    public void setOnchangelistener(InnerAdapter.onchangelistener onchangelistener) {
        this.onchangelistener = onchangelistener;
    }
}
