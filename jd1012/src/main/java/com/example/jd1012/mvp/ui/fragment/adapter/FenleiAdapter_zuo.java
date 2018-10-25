package com.example.jd1012.mvp.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.Zuo;

import java.util.List;

public class FenleiAdapter_zuo extends RecyclerView.Adapter<FenleiAdapter_zuo.zuoHolder>{
    private Context context;
    private List<Zuo.DataBean> list;
    private onclicklistener onclicklistener;

    public FenleiAdapter_zuo(Context context, List<Zuo.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface onclicklistener{
        void onclicklistener(View view,int position);
    }
    public void setOnclicklistener(FenleiAdapter_zuo.onclicklistener onclicklistener) {
        this.onclicklistener = onclicklistener;
    }

    @NonNull
    @Override
    public zuoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_zuo, null);
        zuoHolder holder = new zuoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull zuoHolder holder, int position) {
        holder.text_zuo.setText(list.get(position).getName());
        if(onclicklistener!=null){
            holder.text_zuo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = holder.getLayoutPosition();
                    onclicklistener.onclicklistener(holder.itemView,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class zuoHolder extends RecyclerView.ViewHolder{

        private final TextView text_zuo;

        public zuoHolder(View itemView) {
            super(itemView);
            text_zuo = itemView.findViewById(R.id.text_zuo);
        }
    }
}
