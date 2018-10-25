package com.example.jd1012.mvp.ui.widgh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jd1012.R;

public class toggbutton extends LinearLayout implements View.OnClickListener {

    private Button btn_jia;
    private Button btn_jian;
    private TextView text_num;

    public toggbutton(Context context) {
        super(context);

    }

    public toggbutton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initdata(context);
    }

    private void initdata(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jia_item, this);
        btn_jia = view.findViewById(R.id.btn_jia);
        btn_jian = view.findViewById(R.id.btn_jian);
        text_num = view.findViewById(R.id.text_num);

        btn_jia.setOnClickListener(this);
        btn_jian.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        String num = text_num.getText().toString();
        int i = Integer.parseInt(num);
        switch (v.getId()){
            case R.id.btn_jia:
                if(addAndMinus!=null){
                    addAndMinus.add();
                }
                break;
            case R.id.btn_jian:
                if (i>1){
                    if(addAndMinus!=null){
                        addAndMinus.munus();
                    }
                }
                break;
        }
    }

    //定义接口回调
    private AddAndMinus addAndMinus;
    public interface AddAndMinus{
        void add();
        void munus();
    }
    public void setAddAndMinus(AddAndMinus addAndMinus) {
        this.addAndMinus = addAndMinus;
    }
}
