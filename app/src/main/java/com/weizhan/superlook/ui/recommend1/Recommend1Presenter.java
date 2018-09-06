package com.weizhan.superlook.ui.recommend1;

import android.text.TextUtils;
import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.Recommend1Apis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;
import com.weizhan.superlook.ui.recommend1.viewbinder.Recommend1FooterItemViewBinder;

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

public class Recommend1Presenter extends AbsBasePresenter<Recommend1Contract.View> {

    private static final String TAG = Recommend1Presenter.class.getSimpleName();

    private Recommend1Apis mRecommend1Apis;

    @Inject
    public Recommend1Presenter(Recommend1Apis regionApis) {
        mRecommend1Apis = regionApis;
    }

    @Override
    public void loadData() {
        Items items = new Items();
//        items.add(new Recommend1HeaderItemViewBinder.Recommend1Header());
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
//        items.add(new Recommend1HeaderItemViewBinder.Recommend1Header());
        List<AppRecommend1Show> regionShowList = regionShow.getData();
        int uu = 1;
        for (AppRecommend1Show appRecommend1Show : regionShowList) {
            //banner
            if (appRecommend1Show.getBanner() != null) {
                items.add(appRecommend1Show.getBanner());
            }
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
                Recommend1FooterItemViewBinder.Recommend1Footer footer = new Recommend1FooterItemViewBinder.Recommend1Footer();
                footer.setRecommend1(appRecommend1Show.getTitle().substring(0, appRecommend1Show.getTitle().length() - 1));
                items.add(footer);
            }

            if (uu == 1) {

                AppRecommend1Show.Partition p1 = appRecommend1Show.new Partition();
                p1.setTitle("电影区");
//            p.setLogo(ResourceManager.getRecommend1IconByTitle(appRecommend1Show.getTitle()));
//            p.setLogo(ResourceManager.getRecommend1IconByParam(appRecommend1Show.getParam()));
                appRecommend1Show.setPartition(p1);
                items.add(p1);
                //bodymovie
                AppRecommend1Show.BodyMovie bodyMovie = appRecommend1Show.new BodyMovie();
                bodyMovie.setCover(bodyList.get(0).getCover());
                bodyMovie.setDanmaku(bodyList.get(0).getDanmaku());
                bodyMovie.setFavourite(bodyList.get(0).getFavourite());
                bodyMovie.setGoto(bodyList.get(0).getGoto());
                bodyMovie.setIs_ad(bodyList.get(0).getIs_ad());
                bodyMovie.setParam(bodyList.get(0).getParam());
                bodyMovie.setTitle(bodyList.get(0).getTitle());
                bodyMovie.setPlay(bodyList.get(0).getPlay());
                bodyMovie.setUri(bodyList.get(0).getUri());
                items.add(bodyMovie);
                items.add(bodyMovie);
                items.add(bodyMovie);
                items.add(bodyMovie);
                items.add(bodyMovie);
                items.add(bodyMovie);
                uu = uu + 1;
                Recommend1FooterItemViewBinder.Recommend1Footer footer = new Recommend1FooterItemViewBinder.Recommend1Footer();
                footer.setRecommend1("更多电影");
                items.add(footer);

            }
        }
        return items;
    }


    @Override
    public void releaseData() {

    }
}
