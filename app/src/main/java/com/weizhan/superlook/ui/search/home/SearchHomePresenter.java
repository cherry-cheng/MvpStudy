package com.weizhan.superlook.ui.search.home;

import android.text.TextUtils;
import android.util.Log;

import com.common.base.AbsBasePresenter;
import com.common.util.DateUtil;
import com.weizhan.superlook.model.api.ApiHelper;
import com.weizhan.superlook.model.api.RegionApis;
import com.weizhan.superlook.model.bean.DataListResponse;
import com.weizhan.superlook.model.bean.region.AppRegionShow;
import com.weizhan.superlook.ui.region.viewbinder.RegionFooterItemViewBinder;

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

public class SearchHomePresenter extends AbsBasePresenter<SearchHomeContract.View> {

    private static final String TAG = SearchHomePresenter.class.getSimpleName();

    @Override
    public void releaseData() {

    }
}
