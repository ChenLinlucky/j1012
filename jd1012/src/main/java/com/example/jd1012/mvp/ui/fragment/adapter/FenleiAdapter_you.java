package com.example.jd1012.mvp.ui.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.jd1012.R;
import com.example.jd1012.app.bean.You;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.crypto.Cipher;

public class FenleiAdapter_you extends BaseExpandableListAdapter {
    private Context context;
    private You you;
    public FenleiAdapter_you(Context context, You you) {
        this.context = context;
        this.you = you;
    }

    @Override
    public int getGroupCount() {
        return you.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return you.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return you.getData().get(groupPosition).getList().get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //右条目名
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.you_item, null);
        TextView youitem_name = v.findViewById(R.id.youitem_name);
        youitem_name.setText(you.getData().get(groupPosition).getName());
        return v;
    }

    //child
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.you_item_recyview, null);
        RecyclerView you_recyView = view.findViewById(R.id.you_recyView);
        //网格格式
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        you_recyView.setLayoutManager(manager);
        //RecyclerView的适配器
        youChildAdapter adapter = new youChildAdapter(context,you);
        you_recyView.setAdapter(adapter);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
