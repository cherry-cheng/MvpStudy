package com.weizhan.superlook.model.api;

import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.movie.AppMovie;
import com.weizhan.superlook.model.bean.movie.AppMovieShow;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/9/7.
 */

public interface MovieApis {
    String HOST = "http://app.bilibili.com";

    /**
     * 获取分区
     * http://app.bilibili.com/x/v2/region?build=503000
     * http://app.bilibili.com/x/v2/region?appkey=1d8b6e7d45233436&build=502000&mobi_app=android&platform=android&ts=1493689159000&ver=188703795824240229&sign=89ec282fcc4f06d8d3812dbc9f8456a9
     */
    @GET("/x/v2/region")
    Observable<DataListResponse<AppMovie>> getMovie(@Query("build") String build);

    /**
     * 获取分区列表
     * http://app.bilibili.com/x/v2/show/index?appkey=1d8b6e7d45233436&build=502000&mobi_app=android&platform=android&ts=1493711039000&sign=01cfab07e67d3520363d82636296dc8b
     */
    @GET("/x/v2/show/index")
    Observable<DataListResponse<AppMovieShow>> getMovieShow(@Query("appkey") String appkey,
                                                            @Query("build") String build,
                                                            @Query("mobi_app") String mobi_app,
                                                            @Query("platform") String platform,
                                                            @Query("ts") String ts
    );
}
