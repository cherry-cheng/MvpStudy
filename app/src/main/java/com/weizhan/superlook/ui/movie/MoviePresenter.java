package com.weizhan.superlook.ui.movie;

import android.text.TextUtils;
import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.MovieApis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.movie.AppMovieShow;
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

    private MovieApis mMovieApis;

    @Inject
    public MoviePresenter(MovieApis regionApis) {
        mMovieApis = regionApis;
    }

    @Override
    public void loadData() {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.MovieHeader());
        mView.onDataUpdated(items);
        mMovieApis.getMovieShow(
                ApiHelper.APP_KEY,
                ApiHelper.BUILD,
                ApiHelper.MOBI_APP,
                ApiHelper.PLATFORM,
                DateUtil.getSystemTime())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<DataListResponse<AppMovieShow>, Items>() {
                    @Override
                    public Items apply(@NonNull DataListResponse<AppMovieShow> regionShow) throws Exception {
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

    private Items regionShow2Items(DataListResponse<AppMovieShow> regionShow) {
        Items items = new Items();
//        items.add(new SeriesHeaderItemViewBinder.MovieHeader());
        List<AppMovieShow> regionShowList = regionShow.getData();
        for (AppMovieShow appMovieShow : regionShowList) {
            //banner
            /*if (appMovieShow.getBanner() != null) {
                items.add(appMovieShow.getBanner());
            }*/
            //partition
            AppMovieShow.Partition p = appMovieShow.new Partition();
            p.setTitle(appMovieShow.getTitle());
//            p.setLogo(ResourceManager.getMovieIconByTitle(appMovieShow.getTitle()));
//            p.setLogo(ResourceManager.getMovieIconByParam(appMovieShow.getParam()));
            appMovieShow.setPartition(p);
            items.add(p);

            //body
            List<AppMovieShow.Body> bodyList = appMovieShow.getBody();
            for (AppMovieShow.Body b : bodyList) {
                items.add(b);
            }

            //footer
            if (!TextUtils.equals("活动中心", appMovieShow.getTitle())) {
                MovieFooterItemViewBinder.MovieFooter footer = new MovieFooterItemViewBinder.MovieFooter();
                footer.setMovie(appMovieShow.getTitle().substring(0, appMovieShow.getTitle().length() - 1));
                items.add(footer);
            }
        }
        return items;
    }


    @Override
    public void releaseData() {

    }
}
