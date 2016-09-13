package com.phone1000.groupproject.presenter;

import com.phone1000.groupproject.bean.HorzotalGroupInfo;

/**
 * Created by ${USER_NAME} on 2016/9/12.
 */
public interface IfindGroupPre {
  //获取group的数据
    void getGroupInfo();
    interface  Callback{
        void onSuccess(HorzotalGroupInfo horzotalGroupInfo);
    }
}
