package com.phone1000.groupproject.presenter.impl;

import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.model.impl.IdigithleImpl;
import com.phone1000.groupproject.presenter.ImostnewListPre;
import com.phone1000.groupproject.view.ImostnewlistView;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 * 获取最新listview的类
 */
public class ImostnewImpl implements ImostnewListPre,ImostnewListPre.Callback {
    private ImostnewlistView mImostnewlistView;
    private IdigithleImpl  mIdigtleImpl;

    public ImostnewImpl(ImostnewlistView mImostnewlistView) {
        this.mImostnewlistView = mImostnewlistView;
        mIdigtleImpl = new IdigithleImpl();
    }

    @Override
    public void queryMostNewList(int page, int perPage) {
          mIdigtleImpl.getMostNewList(page,perPage,this);
    }

    @Override
    public void onSuccess(MostnewListInfo mostnewListInfo) {
         mImostnewlistView.queryMostNewList(mostnewListInfo);
    }
}
