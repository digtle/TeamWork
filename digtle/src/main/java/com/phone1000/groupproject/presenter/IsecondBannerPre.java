package com.phone1000.groupproject.presenter;

import com.phone1000.groupproject.bean.LeftBannerInfo;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public interface IsecondBannerPre {
    void querySecondBanner();
    interface  Callback{
        void onSuccess(LeftBannerInfo leftBannerInfo);
    }
}
