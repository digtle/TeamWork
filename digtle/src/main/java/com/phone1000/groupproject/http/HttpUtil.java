package com.phone1000.groupproject.http;

import com.phone1000.groupproject.bean.LeftBannerInfo;
import com.phone1000.groupproject.bean.MostnewListInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public interface HttpUtil {
    @GET("/api/dgtle_api/v1/api.php?REQUESTCODE=49&apikeys=DGTLECOM_APITEST1")
    Observable<LeftBannerInfo>  querySecondBanner();
    @GET("/api/dgtle_api/v1/api.php?REQUESTCODE=46&apikeys=DGTLECOM_APITEST1")
    Observable<MostnewListInfo>  queryMostNewList(@Query("page") int page,@Query("perpage") int perPage);
}
