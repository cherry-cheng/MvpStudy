package com.weizhan.superlook.ui.series;

import android.text.TextUtils;
import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
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
 * Created by Administrator on 2018/9/5.
 */

public class SeriesPresenter extends AbsBasePresenter<SeriesContract.View> {

    private static final String TAG = SeriesPresenter.class.getSimpleName();

    private SeriesApis mSeriesApis;

    @Inject
    public SeriesPresenter(SeriesApis regionApis) {
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

    private Items regionShow2Items(DataListResponse<AppSeriesShow> regionShow) {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.SeriesHeader());
        List<AppSeriesShow> regionShowList = regionShow.getData();
        for (AppSeriesShow appSeriesShow : regionShowList) {
            //banner
            /*if (appSeriesShow.getBanner() != null) {
                items.add(appSeriesShow.getBanner());
            }*/
            //partition
            AppSeriesShow.Partition p = appSeriesShow.new Partition();
//            p.setTitle(appSeriesShow.getTitle());
            p.setTitle("近期热门国产剧");
//            p.setLogo(ResourceManager.getSeriesIconByTitle(appSeriesShow.getTitle()));
//            p.setLogo(ResourceManager.getSeriesIconByParam(appSeriesShow.getParam()));
            appSeriesShow.setPartition(p);
            items.add(p);

            //body
            List<AppSeriesShow.Body> bodyList = appSeriesShow.getBody();
            for (AppSeriesShow.Body b : bodyList) {
                items.add(b);
            }

            //footer
            if (!TextUtils.equals("活动中心", appSeriesShow.getTitle())) {
                SeriesFooterItemViewBinder.SeriesFooter footer = new SeriesFooterItemViewBinder.SeriesFooter();
                footer.setSeries(appSeriesShow.getTitle().substring(0, appSeriesShow.getTitle().length() - 1));
                items.add(footer);
            }
        }
        return items;
    }


    @Override
    public void releaseData() {

    }
}
