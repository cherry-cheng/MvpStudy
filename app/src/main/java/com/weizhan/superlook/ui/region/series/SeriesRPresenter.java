package com.weizhan.superlook.ui.region.series;

import android.text.TextUtils;
import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.common.util.ToastUtils;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.SeriesApis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.series.AppSeriesShow;
import com.weizhan.superlook.ui.series.viewbinder.SeriesFooterItemViewBinder;

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
 * Created by jiayiyang on 17/3/25.
 */

public class SeriesRPresenter extends AbsBasePresenter<SeriesRContract.View> {

    private static final String TAG = SeriesRPresenter.class.getSimpleName();
    private SeriesApis mSeriesApis;

    @Inject
    public SeriesRPresenter(SeriesApis regionApis) {
        mSeriesApis = regionApis;
    }


    @Override
    public void loadData() {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.SeriesHeader());
        mView.onDataUpdated(items);
        mSeriesApis.getSeriesShow(
                ApiHelper.APP_KEY,
                ApiHelper.BUILD,
                ApiHelper.MOBI_APP,
                ApiHelper.PLATFORM,
                DateUtil.getSystemTime())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<DataListResponse<AppSeriesShow>, Items>() {
                    @Override
                    public Items apply(@NonNull DataListResponse<AppSeriesShow> regionShow) throws Exception {
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

    public void LoadAccordParams(int ee) {
        ToastUtils.showLongToast("测试111");
        if (ee == 1) {
            mView.onDataResponse(1);
        }
    }

    private Items regionShow2Items(DataListResponse<AppSeriesShow> regionShow) {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.SeriesHeader());
        List<AppSeriesShow> regionShowList = regionShow.getData();
        for (AppSeriesShow appSeriesShow : regionShowList) {
            //body
            List<AppSeriesShow.Body> bodyList = appSeriesShow.getBody();
            for (AppSeriesShow.Body b : bodyList) {
                items.add(b);
            }
        }
        return items;
    }

    @Override
    public void releaseData() {

    }
}
