package com.example.jd1012.mvp.ui.fragment.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.News3;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter_tuijian extends RecyclerView.Adapter<MyAdapter_tuijian.tuiHoler>{
    private Context context;
    private List<News3.DataBean.TuijianBean.ListBeanX> list;
    public MyAdapter_tuijian(Context context, List<News3.DataBean.TuijianBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public tuiHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tuijian, null);
        tuiHoler holer = new tuiHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull tuiHoler holder, int position) {
        holder.tname_tui.setText(list.get(position).getTitle());
        holder.tprice_tui.setText("价格为"+list.get(position).getPrice()+"元");
        String[] split = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp_tuijian.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class tuiHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final SimpleDraweeView simp_tuijian;
        private final TextView tname_tui;
        private final TextView tprice_tui;

        public tuiHoler(View itemView) {
            super(itemView);
            simp_tuijian = itemView.findViewById(R.id.simp_tuijian);
            tname_tui = itemView.findViewById(R.id.tname_tui);
            tprice_tui = itemView.findViewById(R.id.tprice_tui);
            itemView.setOnClickListener(this);
        }

        //点击详情事件
        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            onitemclick.onclick(position);
        }



    }



    //定义接口回调
    private onitemclick onitemclick;
    public interface onitemclick{
        void onclick(int position);
    }
    public void setOnitemclick(MyAdapter_tuijian.onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }
}
