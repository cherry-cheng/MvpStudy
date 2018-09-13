package com.weizhan.superlook.ui.mine;

import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.RegionApis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.region.AppRegionShow;

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
 * Created by Android_ZzT on 17/7/6.
 */

public class MinePresenter extends AbsBasePresenter<MineContract.View> {

    private static final String TAG = MinePresenter.class.getSimpleName();

    private RegionApis mRegionApis;

    @Inject
    public MinePresenter(RegionApis regionApis) {
        mRegionApis = regionApis;
    }

    @Override
    public void loadData() {
        Items items = new Items();
//        items.add(new RegionHeaderItemViewBinder.RegionHeader());
        mView.onDataUpdated(items);
        mRegionApis.getRegionShow(
                ApiHelper.APP_KEY,
                ApiHelper.BUILD,
                ApiHelper.MOBI_APP,
                ApiHelper.PLATFORM,
                DateUtil.getSystemTime())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<DataListResponse<AppRegionShow>, Items>() {
                    @Override
                    public Items apply(@NonNull DataListResponse<AppRegionShow> regionShow) throws Exception {
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

    private Items regionShow2Items(DataListResponse<AppRegionShow> regionShow) {
        Items items = new Items();
//        items.add(new RegionHeaderItemViewBinder.RegionHeader());
        List<AppRegionShow> regionShowList = regionShow.getData();
        for (AppRegionShow appRegionShow : regionShowList) {
            //partition
            AppRegionShow.Partition p = appRegionShow.new Partition();
            p.setTitle(appRegionShow.getTitle());
//            p.setLogo(ResourceManager.getRegionIconByTitle(appRegionShow.getTitle()));
//            p.setLogo(ResourceManager.getRegionIconByParam(appRegionShow.getParam()));
            appRegionShow.setPartition(p);
            items.add(p);

            //body
            List<AppRegionShow.Body> bodyList = appRegionShow.getBody();
            for (AppRegionShow.Body b : bodyList) {
                items.add(b);
            }
        }
        return items;
    }


    @Override
    public void releaseData() {

    }
}
