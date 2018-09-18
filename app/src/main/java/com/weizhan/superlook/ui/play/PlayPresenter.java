package com.weizhan.superlook.ui.play;

import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.Recommend1Apis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;

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
 * Created by Administrator on 2018/9/18.
 */

public class PlayPresenter extends AbsBasePresenter<PlayContract.View> implements PlayContract.Presenter {

    private static final String TAG = PlayPresenter.class.getSimpleName();

    private Recommend1Apis mRecommend1Apis;

    @Inject
    public PlayPresenter(Recommend1Apis regionApis) {
        mRecommend1Apis = regionApis;
    }

    @Override
    public void loadData() {
        Items items = new Items();
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
        List<AppRecommend1Show> regionShowList = regionShow.getData();
        for (AppRecommend1Show appRecommend1Show : regionShowList) {
            //body
            List<AppRecommend1Show.Body> bodyList = appRecommend1Show.getBody();
            for (AppRecommend1Show.Body b : bodyList) {
                items.add(b);
            }
            break;
        }
        return items;
    }


    @Override
    public void releaseData() {

    }
}
