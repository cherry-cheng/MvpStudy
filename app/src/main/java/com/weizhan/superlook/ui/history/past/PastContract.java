package com.weizhan.superlook.ui.history.past;

import com.common.base.BasePresenter;
import com.common.base.BaseView;

import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/5.
 */

public class PastContract {
    interface View extends BaseView {

        void onDataUpdated(Items items);

        void showLoadFailed();
    }

    interface Presenter extends BasePresenter {

    }
}
