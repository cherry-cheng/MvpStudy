package com.weizhan.superlook.ui.test.mvp.contract;

import com.weizhan.superlook.model.bean.WeiXinJingXuanBean;
import com.common.base.BasePresenter;
import com.common.base.BaseView;

import java.util.List;

/**
 * Created by jiayiyang on 17/3/25.
 */

public interface MvpStructureContract {

    interface View extends BaseView {

        void updateData(List<WeiXinJingXuanBean.NewsList> list);

        void setRefreshing();

    }

    interface Presenter extends BasePresenter {

    }

}
