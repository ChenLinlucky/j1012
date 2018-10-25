package com.example.jd1012.mvp.ui.fragment.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.News2;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.jiuHolder>{
    private Context context;
    private List<News2.DataBean> list;
    public MyAdapter(Context context, List<News2.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public jiuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiugongge, null);
        jiuHolder holder = new jiuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull jiuHolder holder, int position) {
        holder.jiugongge_name.setText(list.get(position).getName());
       String[] split = list.get(position).getIcon().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class jiuHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;
        private final TextView jiugongge_name;


        public jiuHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.simp);
            jiugongge_name = itemView.findViewById(R.id.jiugongge_name);
        }
    }
}
