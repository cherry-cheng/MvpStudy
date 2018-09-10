package com.weizhan.superlook.ui.search.home;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.base.BaseMvpFragment;
import com.common.util.ToastUtils;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.search.HotWord;
import com.weizhan.superlook.model.bean.search.SearchKey;
import com.weizhan.superlook.model.event.ClickMessage;
import com.weizhan.superlook.ui.search.viewbinder.HotSearchViewBinder;
import com.weizhan.superlook.util.RealmHelper;
import com.weizhan.superlook.widget.adapter.CommonAdapter;
import com.weizhan.superlook.widget.adapter.HotWordAdapter;
import com.weizhan.superlook.widget.textview.HotWordListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.Items;

public class SearchHomeFragment extends BaseMvpFragment<SearchHomePresenter> implements SearchHomeContract.View, HotWordListView.OnItemClickListener {

    public static final String TAG = SearchHomeFragment.class.getSimpleName();

    @BindView(R.id.search_history)
    HotWordListView search_history;
    @BindView(R.id.hotRecyclerView)
    RecyclerView hotRecyclerView;

    private CommonAdapter mAdapter;
    private HotWordAdapter historyAdapter;
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

        //装入假数据
        List<SearchKey> historySearch = new ArrayList<SearchKey>();
        for (int i = 0; i < 6; i++) {
            SearchKey searchKey = new SearchKey();
            searchKey.setSearchKey("西红柿首富");
            searchKey.setInsertTime(System.currentTimeMillis());
            historySearch.add(searchKey);
        }
        historyAdapter = new HotWordAdapter(getContext(), historySearch);

        search_history.setAdapter(historyAdapter);
        search_history.setOnItemClickListener(this);
    }

    private void setHistory() {
        final List<SearchKey> searchHistory = RealmHelper.getInstance().getSearchHistoryListAll();

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

    @Override
    public void onItemClick(View view, int position) {
        ToastUtils.showLongToast("点击了历史记录");
    }
}
