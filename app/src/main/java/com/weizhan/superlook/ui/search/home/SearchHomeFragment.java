package com.weizhan.superlook.ui.search.home;

import com.common.base.BaseMvpFragment;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import me.drakeet.multitype.Items;

public class SearchHomeFragment extends BaseMvpFragment<SearchHomePresenter> implements SearchHomeContract.View {

    public static final String TAG = SearchHomeFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_home;
    }

    @Override
    protected void initInject() {
        App.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDataUpdated(Items items) {

    }

    @Override
    public void showLoadFailed() {

    }

}
