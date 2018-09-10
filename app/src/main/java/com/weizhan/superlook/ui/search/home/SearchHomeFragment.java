package com.weizhan.superlook.ui.search.home;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.common.base.BaseMvpFragment;
import com.common.util.ToastUtils;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.region.AppRegionShow;
import com.weizhan.superlook.model.bean.search.HotWord;
import com.weizhan.superlook.model.event.ClickMessage;
import com.weizhan.superlook.ui.region.RegionIndexItemDecoration;
import com.weizhan.superlook.ui.region.viewbinder.RegionBannerItemViewBinder;
import com.weizhan.superlook.ui.region.viewbinder.RegionBodyItemViewBinder;
import com.weizhan.superlook.ui.region.viewbinder.RegionFooterItemViewBinder;
import com.weizhan.superlook.ui.region.viewbinder.RegionPartitionItemViewBinder;
import com.weizhan.superlook.ui.search.viewbinder.HotSearchViewBinder;
import com.weizhan.superlook.widget.adapter.CommonAdapter;
import com.weizhan.superlook.widget.textview.HotWordListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import me.drakeet.multitype.Items;

public class SearchHomeFragment extends BaseMvpFragment<SearchHomePresenter> implements SearchHomeContract.View {

    public static final String TAG = SearchHomeFragment.class.getSimpleName();

    @BindView(R.id.search_history)
    HotWordListView search_history;
    @BindView(R.id.hotRecyclerView)
    RecyclerView hotRecyclerView;

    private CommonAdapter mAdapter;
    private static final int SPAN_COUNT = 1;

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
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        hotRecyclerView.setLayoutManager(layoutManager);
        hotRecyclerView.addItemDecoration(new SearchIndexItemDecoration());
        hotRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        mAdapter = new CommonAdapter();
        mAdapter.register(HotWord.class, new HotSearchViewBinder());
        mAdapter.setScrollSaveStrategyEnabled(true);
        hotRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDataUpdated(Items items) {
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadFailed() {
        mAdapter.showLoadFailed();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(ClickMessage event) {
        ToastUtils.showLongToast("abccc");
    }
}
