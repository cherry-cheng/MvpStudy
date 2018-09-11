package com.weizhan.superlook.ui.region.series;

import com.common.base.BasePresenter;
import com.common.base.BaseView;

import me.drakeet.multitype.Items;

/**
 * Created by jiayiyang on 17/3/25.
 */

public interface SeriesRContract {

    interface View extends BaseView {
        void onDataUpdated(Items items);

        void showLoadFailed();
    }

    interface Presenter extends BasePresenter {

    }

}
