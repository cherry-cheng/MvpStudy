package com.weizhan.superlook.ui.series;

import com.common.base.BasePresenter;
import com.common.base.BaseView;

import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/5.
 */

public class SeriesContract {
    interface View extends BaseView {

        void onDataUpdated(Items items);

        void showLoadFailed();
    }

    interface Presenter extends BasePresenter {

    }
}
