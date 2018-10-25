package com.example.jd1012.mvp.model.api;

import com.example.jd1012.app.bean.AddShopCartBean;
import com.example.jd1012.app.bean.News;
import com.example.jd1012.app.bean.News2;
import com.example.jd1012.app.bean.News3;
import com.example.jd1012.app.bean.Regin;
import com.example.jd1012.app.bean.Sousuo;
import com.example.jd1012.app.bean.Xiangqing;
import com.example.jd1012.app.bean.You;
import com.example.jd1012.app.bean.Zuo;
import com.example.jd1012.app.bean.Login;


import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    //轮播
    @GET("ad/getAd")
    Observable<News> getLunbo();
    //九宫格
    @GET("product/getCatagory")
    Observable<News2> getJiugongge();
    //商品推荐
    @GET("home/getHome?")
    Observable<News3> getTuijian();
    //动态搜索
    @GET("product/searchProducts?")
    Observable<Sousuo> getShou(@Query("keywords")String keywords);
    //购物车
    @GET("product/getCarts")
    Observable<ResponseBody> getshop(@Query("uid")int uid,@Query("tooken")String token);
    //分类左侧
    @GET("product/getCatagory")
    Observable<Zuo> getZuob();
    //右侧子分类
    @GET("product/getProductCatagory")
    Observable<You> getYoub(@Query("cid")int id);
   //登录
    @GET("user/login")
    Observable<Login> getlogin(@Query("mobile") String mobile, @Query("password") String password);
   //注册
    @GET("user/reg")
    Observable<Regin> getregin(@Query("mobile") String mobile, @Query("password") String password);
    //商品详情
    @GET("product/getProductDetail")
    Observable<Xiangqing> getxiangqing(@Query("pid")int pid);
    //加入购物车
    @GET("product/addCart")
    Observable<AddShopCartBean> addsp(@Query("uid") int uid,@Query("pid")int pid);

}
