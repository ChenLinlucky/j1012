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
import com.example.jd1012.app.bean.You;
import com.facebook.drawee.view.SimpleDraweeView;

public class youChildAdapter extends RecyclerView.Adapter<youChildAdapter.childHolder>{
    private Context context;
    private You you;
    public youChildAdapter(Context context, You you) {
        this.context = context;
        this.you = you;
    }

    @NonNull
    @Override
    public childHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.youhai, null);
        childHolder holder = new childHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull childHolder holder, int position) {
        holder.child_name.setText(you.getData().get(position).getList().get(position).getName());
        String[] split = you.getData().get(position).getList().get(position).getIcon().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.child_simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return you.getData().size();
    }


    class childHolder extends RecyclerView.ViewHolder{

        private final TextView child_name;
        private final SimpleDraweeView child_simp;

        public childHolder(View itemView) {
            super(itemView);
            child_name = itemView.findViewById(R.id.child_name);
            child_simp = itemView.findViewById(R.id.child_simp);
        }
    }
}
