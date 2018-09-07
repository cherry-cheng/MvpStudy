package com.weizhan.superlook.ui.movie;

import android.text.TextUtils;
import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.Recommend1Apis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;
import com.weizhan.superlook.ui.movie.viewbinder.MovieFooterItemViewBinder;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/5.
 */

public class MoviePresenter extends AbsBasePresenter<MovieContract.View> {

    private static final String TAG = MoviePresenter.class.getSimpleName();

    private Recommend1Apis mRecommend1Apis;

    @Inject
    public MoviePresenter(Recommend1Apis regionApis) {
        mRecommend1Apis = regionApis;
    }

    @Override
    public void loadData() {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.Recommend1Header());
        mView.onDataUpdated(items);
        mRecommend1Apis.getRecommend1Show(
                ApiHelper.APP_KEY,
                ApiHelper.BUILD,
                ApiHelper.MOBI_APP,
                ApiHelper.PLATFORM,
                DateUtil.getSystemTime())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<DataListResponse<AppRecommend1Show>, Items>() {
                    @Override
                    public Items apply(@NonNull DataListResponse<AppRecommend1Show> regionShow) throws Exception {
                        return regionShow2Items(regionShow);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Items>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        registerRx(d);
                    }

                    @Override
                    public void onNext(@NonNull Items items) {
                        mView.onDataUpdated(items);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError");
                        e.printStackTrace();
                        mView.showLoadFailed();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");

                    }
                });
    }

    private Items regionShow2Items(DataListResponse<AppRecommend1Show> regionShow) {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.Recommend1Header());
        List<AppRecommend1Show> regionShowList = regionShow.getData();
        for (AppRecommend1Show appRecommend1Show : regionShowList) {
            //banner
            /*if (appRecommend1Show.getBanner() != null) {
                items.add(appRecommend1Show.getBanner());
            }*/
            //partition
            AppRecommend1Show.Partition p = appRecommend1Show.new Partition();
            p.setTitle(appRecommend1Show.getTitle());
//            p.setLogo(ResourceManager.getRecommend1IconByTitle(appRecommend1Show.getTitle()));
//            p.setLogo(ResourceManager.getRecommend1IconByParam(appRecommend1Show.getParam()));
            appRecommend1Show.setPartition(p);
            items.add(p);

            //body
            List<AppRecommend1Show.Body> bodyList = appRecommend1Show.getBody();
            for (AppRecommend1Show.Body b : bodyList) {
                items.add(b);
            }

            //footer
            if (!TextUtils.equals("活动中心", appRecommend1Show.getTitle())) {
                MovieFooterItemViewBinder.Recommend1Footer footer = new MovieFooterItemViewBinder.Recommend1Footer();
                footer.setRecommend1(appRecommend1Show.getTitle().substring(0, appRecommend1Show.getTitle().length() - 1));
                items.add(footer);
            }
        }
        return items;
    }


    @Override
    public void releaseData() {

    }
}
