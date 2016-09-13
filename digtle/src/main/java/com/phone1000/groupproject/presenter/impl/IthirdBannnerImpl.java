package com.phone1000.groupproject.presenter.impl;

import com.phone1000.groupproject.bean.LeftBannerInfo;
import com.phone1000.groupproject.model.impl.IdigithleImpl;
import com.phone1000.groupproject.presenter.IthirdBanerPre;
import com.phone1000.groupproject.view.IthirdBannerView;

/**
 * Created by ${USER_NAME} on 2016/9/12.
 */
public class IthirdBannnerImpl implements IthirdBanerPre,IthirdBanerPre.CallBack {
    private IthirdBannerView mIthirdBanerView;
    private IdigithleImpl mIdigithleImpl;

    public IthirdBannnerImpl(IthirdBannerView mIthirdBanerView) {
        this.mIthirdBanerView = mIthirdBanerView;
        mIdigithleImpl = new IdigithleImpl();
    }

    @Override
    public void getThirdBanner() {
             mIdigithleImpl.getFindBanner(this);
    }


    @Override
    public void onSucess(LeftBannerInfo leftBannerInfo) {
        mIthirdBanerView.quereyThirdBanner(leftBannerInfo);
    }
}
