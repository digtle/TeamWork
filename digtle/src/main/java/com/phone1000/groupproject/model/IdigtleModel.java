package com.phone1000.groupproject.model;

import com.phone1000.groupproject.presenter.ImostnewListPre;
import com.phone1000.groupproject.presenter.IsecondBannerPre;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public interface IdigtleModel {
    //获取最新头部视图的数据
    void getSecondBanner(IsecondBannerPre.Callback callback);
    //获取最新的listview的数据
    void getMostNewList(int page,int perpage,ImostnewListPre.Callback callback);

}
