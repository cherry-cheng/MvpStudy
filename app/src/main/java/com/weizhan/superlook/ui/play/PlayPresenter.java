package com.weizhan.superlook.ui.play;

import com.common.base.AbsBasePresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/9/18.
 */

public class PlayPresenter extends AbsBasePresenter<PlayContract.View> implements PlayContract.Presenter {

    private static final String TAG = PlayPresenter.class.getSimpleName();

    @Inject
    public PlayPresenter() {

    }


    @Override
    public void loadData() {

    }

    @Override
    public void releaseData() {

    }
}
