package com.phone1000.groupproject.presenter;

import com.phone1000.groupproject.bean.LeftBannerInfo;

/**
 * Created by ${USER_NAME} on 2016/9/12.
 */
public interface IthirdBanerPre {
    //获取第三个页面头部的viewpager的视图
    void getThirdBanner();
    interface  CallBack{
        void onSucess(LeftBannerInfo leftBannerInfo);
    }
}
