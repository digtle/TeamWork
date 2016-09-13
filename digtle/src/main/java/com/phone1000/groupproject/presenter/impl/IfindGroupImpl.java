package com.phone1000.groupproject.presenter.impl;

import com.phone1000.groupproject.bean.HorzotalGroupInfo;
import com.phone1000.groupproject.model.impl.IdigithleImpl;
import com.phone1000.groupproject.presenter.IfindGroupPre;
import com.phone1000.groupproject.view.IfindGroupView;

/**
 * Created by ${USER_NAME} on 2016/9/12.
 */
public class IfindGroupImpl implements IfindGroupPre ,IfindGroupPre.Callback{
     private IfindGroupView mIfindGroupView;
     private IdigithleImpl mIdigothleImpl;

    public IfindGroupImpl(IfindGroupView mIfindGroupView) {
        this.mIfindGroupView = mIfindGroupView;
        mIdigothleImpl = new IdigithleImpl();
    }

    @Override
    public void getGroupInfo() {
        mIdigothleImpl.getFindGroupInfo(this);
    }

    @Override
    public void onSuccess(HorzotalGroupInfo horzotalGroupInfo) {
     mIfindGroupView.getFindGroup(horzotalGroupInfo);
    }
}
