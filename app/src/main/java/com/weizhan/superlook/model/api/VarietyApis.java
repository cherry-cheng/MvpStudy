package com.weizhan.superlook.model.api;

import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.series.AppSeries;
import com.weizhan.superlook.model.bean.series.AppSeriesShow;
import com.weizhan.superlook.model.bean.variety.AppVariety;
import com.weizhan.superlook.model.bean.variety.AppVarietyShow;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/9/7.
 */

public interface VarietyApis {
    String HOST = "http://app.bilibili.com";

    /**
     * 获取分区
     * http://app.bilibili.com/x/v2/region?build=503000
     * http://app.bilibili.com/x/v2/region?appkey=1d8b6e7d45233436&build=502000&mobi_app=android&platform=android&ts=1493689159000&ver=188703795824240229&sign=89ec282fcc4f06d8d3812dbc9f8456a9
     */
    @GET("/x/v2/region")
    Observable<DataListResponse<AppVariety>> getVariety(@Query("build") String build);

    /**
     * 获取分区列表
     * http://app.bilibili.com/x/v2/show/index?appkey=1d8b6e7d45233436&build=502000&mobi_app=android&platform=android&ts=1493711039000&sign=01cfab07e67d3520363d82636296dc8b
     */
    @GET("/x/v2/show/index")
    Observable<DataListResponse<AppVarietyShow>> getVarietyShow(@Query("appkey") String appkey,
                                                                @Query("build") String build,
                                                                @Query("mobi_app") String mobi_app,
                                                                @Query("platform") String platform,
                                                                @Query("ts") String ts
    );
}
