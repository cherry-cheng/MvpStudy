package com.weizhan.superlook.ui.variety;

import android.text.TextUtils;
import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.VarietyApis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.variety.AppVarietyShow;
import com.weizhan.superlook.ui.variety.viewbinder.VarietyFooterItemViewBinder;

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

public class VarietyPresenter extends AbsBasePresenter<VarietyContract.View> {

    private static final String TAG = VarietyPresenter.class.getSimpleName();

    private VarietyApis mVarietyApis;

    @Inject
    public VarietyPresenter(VarietyApis regionApis) {
        mVarietyApis = regionApis;
    }

    @Override
    public void loadData() {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.VarietyHeader());
        mView.onDataUpdated(items);
        mVarietyApis.getVarietyShow(
                ApiHelper.APP_KEY,
                ApiHelper.BUILD,
                ApiHelper.MOBI_APP,
                ApiHelper.PLATFORM,
                DateUtil.getSystemTime())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<DataListResponse<AppVarietyShow>, Items>() {
                    @Override
                    public Items apply(@NonNull DataListResponse<AppVarietyShow> regionShow) throws Exception {
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

    private Items regionShow2Items(DataListResponse<AppVarietyShow> regionShow) {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.VarietyHeader());
        List<AppVarietyShow> regionShowList = regionShow.getData();
        for (AppVarietyShow appVarietyShow : regionShowList) {
            //banner
            /*if (appVarietyShow.getBanner() != null) {
                items.add(appVarietyShow.getBanner());
            }*/
            //partition
            AppVarietyShow.Partition p = appVarietyShow.new Partition();
//            p.setTitle(appVarietyShow.getTitle());
            p.setTitle("近期热门内地综艺");
//            p.setLogo(ResourceManager.getVarietyIconByTitle(appVarietyShow.getTitle()));
//            p.setLogo(ResourceManager.getVarietyIconByParam(appVarietyShow.getParam()));
            appVarietyShow.setPartition(p);
            items.add(p);

            //body
            List<AppVarietyShow.Body> bodyList = appVarietyShow.getBody();
            for (AppVarietyShow.Body b : bodyList) {
                items.add(b);
            }

            //footer
            if (!TextUtils.equals("活动中心", appVarietyShow.getTitle())) {
                VarietyFooterItemViewBinder.VarietyFooter footer = new VarietyFooterItemViewBinder.VarietyFooter();
                footer.setVariety(appVarietyShow.getTitle().substring(0, appVarietyShow.getTitle().length() - 1));
                items.add(footer);
            }
        }
        return items;
    }


    @Override
    public void releaseData() {

    }
}
