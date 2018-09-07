package com.weizhan.superlook.ui.search;

import com.common.base.AbsBasePresenter;

import javax.inject.Inject;

/**
 * Created by jiayiyang on 17/3/25.
 */

public class SearchPresenter extends AbsBasePresenter<SearchContract.View> implements SearchContract.Presenter {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    @Inject
    public SearchPresenter() {

    }


    @Override
    public void loadData() {

    }

    @Override
    public void releaseData() {

    }
}
