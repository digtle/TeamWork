package com.phone1000.groupproject.model.impl;

import com.phone1000.groupproject.bean.HorzotalGroupInfo;
import com.phone1000.groupproject.bean.LeftBannerInfo;
import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.http.HttpServer;
import com.phone1000.groupproject.model.IdigtleModel;
import com.phone1000.groupproject.presenter.IfindGroupPre;
import com.phone1000.groupproject.presenter.ImostnewListPre;
import com.phone1000.groupproject.presenter.IsecondBannerPre;
import com.phone1000.groupproject.presenter.IthirdBanerPre;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public class IdigithleImpl implements IdigtleModel {
 //获取最新的头部视图的数据
    @Override
    public void getSecondBanner(final IsecondBannerPre.Callback callback) {
        HttpServer.create().querySecondBanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<LeftBannerInfo>() {
                    @Override
                    public void call(LeftBannerInfo leftBannerInfo) {
                        callback.onSuccess(leftBannerInfo);
                    }
                });
    }
    //获取最新的listview的数据
    @Override
    public void getMostNewList(int page, int perpage, final ImostnewListPre.Callback callback) {
        HttpServer.create().queryMostNewList(page,page).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<MostnewListInfo>() {
                    @Override
                    public void call(MostnewListInfo mostnewListInfo) {
                       callback.onSuccess(mostnewListInfo);
                    }
                });
    }
//获取第三个页面的头部viewpager的视图
    @Override
    public void getFindBanner(final IthirdBanerPre.CallBack callBack) {
           HttpServer.create().queryThirdBannerInfo()
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribeOn(Schedulers.io()).subscribe(new Action1<LeftBannerInfo>() {
               @Override
               public void call(LeftBannerInfo leftBannerInfo) {
                      callBack.onSucess(leftBannerInfo);
               }
           });


    }

    @Override
    public void getFindGroupInfo(final IfindGroupPre.Callback callback) {
                 HttpServer.create().queryHorizatlInfo()
                         .observeOn(AndroidSchedulers.mainThread())
                         .subscribeOn(Schedulers.io())
                         .subscribe(new Action1<HorzotalGroupInfo>() {
                             @Override
                             public void call(HorzotalGroupInfo horzotalGroupInfo) {
                                  callback.onSuccess(horzotalGroupInfo);
                             }
                         });
    }


}
