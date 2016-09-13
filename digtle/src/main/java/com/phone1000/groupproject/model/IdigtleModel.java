package com.phone1000.groupproject.model;

import com.phone1000.groupproject.presenter.IfindGroupPre;
import com.phone1000.groupproject.presenter.ImostnewListPre;
import com.phone1000.groupproject.presenter.IsecondBannerPre;
import com.phone1000.groupproject.presenter.IthirdBanerPre;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public interface IdigtleModel {
    //获取最新头部视图的数据
    void getSecondBanner(IsecondBannerPre.Callback callback);
    //获取最新的listview的数据
    void getMostNewList(int page,int perpage,ImostnewListPre.Callback callback);
   //获取第三个页面的viewpager的数据
    void getFindBanner(IthirdBanerPre.CallBack callBack);
    //获取发现界面的中的视图的数据
    void getFindGroupInfo(IfindGroupPre.Callback callback);
}
