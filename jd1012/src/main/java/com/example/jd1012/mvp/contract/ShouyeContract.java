package com.example.jd1012.mvp.contract;

import com.example.jd1012.app.bean.News;
import com.example.jd1012.app.bean.News2;
import com.example.jd1012.app.bean.News3;
import com.example.jd1012.app.bean.Sousuo;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;


public interface ShouyeContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void showdata(News news);
        void showjiu(News2 news2);
        void shoutui(News3 news3);
       // void sousuo(Sousuo sousuo);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<News> responsedataLunbo();
        Observable<News2> responsedatajiugongge();
        Observable<News3> responsedatatuijian();
       // Observable<Sousuo> responsedataSousuo(String keywords);
     }
}
