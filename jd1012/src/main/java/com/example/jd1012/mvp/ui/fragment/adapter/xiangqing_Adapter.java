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
import com.example.jd1012.app.bean.Xiangqing;
import com.example.jd1012.mvp.ui.activity.xiangqingActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class xiangqing_Adapter extends RecyclerView.Adapter<xiangqing_Adapter.xiangHolder>{
    private Context context;
    private List<Xiangqing.DataBean> list;

    public xiangqing_Adapter(Context context, List<Xiangqing.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public xiangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.xiangqing_item, null);
        xiangHolder holder = new xiangHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull xiangHolder holder, int position) {
        holder.xiangqing_name.setText(list.get(position).getTitle());
        holder.xiangqing_price.setText("价格为"+list.get(position).getPrice()+"元");
        String[] split = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.xiiangqing_simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class xiangHolder extends RecyclerView.ViewHolder{

        private final TextView xiangqing_name;
        private final TextView xiangqing_price;
        private final SimpleDraweeView xiiangqing_simp;

        public xiangHolder(View itemView) {
            super(itemView);
            xiiangqing_simp = itemView.findViewById(R.id.xiiangqing_simp);
            xiangqing_name = itemView.findViewById(R.id.xiangqing_name);
            xiangqing_price = itemView.findViewById(R.id.xiangqing_price);
        }
    }
}
