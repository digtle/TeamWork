package com.phone1000.groupproject.presenter.impl;

import com.phone1000.groupproject.bean.LeftBannerInfo;
import com.phone1000.groupproject.model.impl.IdigithleImpl;
import com.phone1000.groupproject.presenter.IsecondBannerPre;
import com.phone1000.groupproject.view.IsecondBannerView;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public class IsecondBannerImpl implements IsecondBannerPre,IsecondBannerPre.Callback {
private IdigithleImpl mDigthleImpl;
    private IsecondBannerView mIsecondBannerView;

    public IsecondBannerImpl(IsecondBannerView mIsecondBannerView) {
        this.mIsecondBannerView = mIsecondBannerView;
        mDigthleImpl = new IdigithleImpl();
    }

    @Override
    public void querySecondBanner() {
       mDigthleImpl.getSecondBanner(this);
    }

    @Override
    public void onSuccess(LeftBannerInfo leftBannerInfo) {
        mIsecondBannerView.getSecondBanner(leftBannerInfo);
    }
}
