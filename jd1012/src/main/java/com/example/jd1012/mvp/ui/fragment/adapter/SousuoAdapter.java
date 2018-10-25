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
import com.example.jd1012.app.bean.Sousuo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class SousuoAdapter extends RecyclerView.Adapter<SousuoAdapter.SouHolder>{
    private Context context;
    private List<Sousuo.DataBean> list;
    public SousuoAdapter(Context context, List<Sousuo.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SouHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sousuo_item, null);
        SouHolder holder = new SouHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SouHolder holder, int position) {
        holder.tname_sousuo.setText(list.get(position).getTitle());
        holder.tprice_sousuo.setText(list.get(position).getPrice()+"");
        String[] split = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp_sousuo.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SouHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp_sousuo;
        private final TextView tname_sousuo;
        private final TextView tprice_sousuo;

        public SouHolder(View itemView) {
            super(itemView);
            simp_sousuo = itemView.findViewById(R.id.simp_sousuo);
            tname_sousuo = itemView.findViewById(R.id.tname_sousuo);
            tprice_sousuo = itemView.findViewById(R.id.tprice_sousuo);
        }
    }
}
