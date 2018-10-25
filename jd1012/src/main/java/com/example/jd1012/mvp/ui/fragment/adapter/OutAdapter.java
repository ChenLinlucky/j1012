package com.example.jd1012.mvp.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.Shop;

import java.util.List;

public class OutAdapter extends RecyclerView.Adapter<OutAdapter.outHolder>{
    private Context context;
    private List<Shop.DataBean> list;
    public OutAdapter(Context context, List<Shop.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public outHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.out_shangjia, null);
        outHolder holder = new outHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull outHolder holder, int position) {
        holder.cb_02.setChecked(list.get(position).isOutChecked());
        holder.cb_02.setText(list.get(position).getSellerName());

        InnerAdapter innerAdapter = new InnerAdapter(context, list.get(position).getList());
        holder.recyView_out.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        holder.recyView_out.setAdapter(innerAdapter);



        holder.cb_02.setOnClickListener(null);//首先给商家默认为空
        holder.cb_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //商家控制子条目
                boolean checked = holder.cb_02.isChecked();
                list.get(position).setOutChecked(checked);//改变默认值
                if (checked){
                    //判断里层的选中，
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerChecked(true);
                    }
                }else{
                    for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                        list.get(holder.getLayoutPosition()).getList().get(i).setInnerChecked(false);
                    }
                }
                //回传的商家的状态值
                onchangeclicklistener.onitemchecked(holder.getLayoutPosition(),checked);
            }
        });




        //外层获取里层  回调
        innerAdapter.setOnchangelistener(new InnerAdapter.onchangelistener() {
            @Override
            public void onchecked(int layoutPosition, boolean checked) {
                //接收状态及其下标
                //首先定义一个标识值
                boolean b= true;
                //获取里层的选中状态
                for (int i = 0; i < list.get(holder.getLayoutPosition()).getList().size(); i++) {
                    boolean innerChecked = list.get(holder.getLayoutPosition()).getList().get(i).isInnerChecked();
                    b=(b&innerChecked);
                }
                holder.cb_02.setChecked(b);//给商家赋值
                list.get(position).setOutChecked(b);//商家获得到的值要改变默认值

                onchangeclicklistener.onchecked(holder.getLayoutPosition(),checked);//把状态值回传给商家全选框
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class outHolder extends RecyclerView.ViewHolder{

        private final CheckBox cb_02;
        private final RecyclerView recyView_out;

        public outHolder(View itemView) {
            super(itemView);
            cb_02 = itemView.findViewById(R.id.cb_02);
            recyView_out = itemView.findViewById(R.id.recyView_out);
        }
    }


    //定义接口回调
    private onchangeclicklistener onchangeclicklistener;
    public interface onchangeclicklistener{
        void onchecked(int layoutPosition, boolean checked);
        void onitemchecked(int layoutPosition, boolean checked);
    }
    public void setOnchangeclicklistener(OutAdapter.onchangeclicklistener onchangeclicklistener) {
        this.onchangeclicklistener = onchangeclicklistener;
    }
}
