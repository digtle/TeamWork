package com.phone1000.groupproject.presenter;

import com.phone1000.groupproject.bean.MostnewListInfo;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public interface ImostnewListPre {
    void  queryMostNewList(int page,int perPage);
    interface  Callback{
        void onSuccess(MostnewListInfo mostnewListInfo);
    }
}
