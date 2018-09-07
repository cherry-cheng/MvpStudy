package com.weizhan.superlook.ui.series;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.common.base.BaseMvpFragment;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;
import com.weizhan.superlook.ui.series.viewbinder.SeriesBannerItemViewBinder;
import com.weizhan.superlook.ui.series.viewbinder.SeriesBodyItemViewBinder;
import com.weizhan.superlook.ui.series.viewbinder.SeriesFooterItemViewBinder;
import com.weizhan.superlook.ui.series.viewbinder.SeriesPartitionItemViewBinder;
import com.weizhan.superlook.widget.adapter.CommonAdapter;

import butterknife.BindView;
import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/5.
 */

public class SeriesFragment extends BaseMvpFragment<SeriesPresenter> implements SeriesContract.View {

    public static final String TAG = SeriesFragment.class.getSimpleName();

    private static final int SPAN_COUNT = 2;

    @BindView(R.id.rv_region)
    RecyclerView mRecyclerView;

    private CommonAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_series;
    }

    @Override
    protected void initInject() {
        App.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = mAdapter.getItems().get(position);
                return item instanceof AppRecommend1Show.Body ? 1 : SPAN_COUNT;
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SeriesIndexItemDecoration());
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        mAdapter = new CommonAdapter();
//        mAdapter.register(SeriesHeaderItemViewBinder.Recommend1Header.class, new SeriesHeaderItemViewBinder());
//        mAdapter.register(AppRecommend1Show.Banner.class, new SeriesBannerItemViewBinder());
        mAdapter.register(AppRecommend1Show.Partition.class, new SeriesPartitionItemViewBinder());
        mAdapter.register(AppRecommend1Show.Body.class, new SeriesBodyItemViewBinder());
        mAdapter.register(SeriesFooterItemViewBinder.Recommend1Footer.class, new SeriesFooterItemViewBinder());
        mAdapter.setScrollSaveStrategyEnabled(true);
        mRecyclerView.setAdapter(mAdapter);
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
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadFailed() {
        mAdapter.showLoadFailed();
    }
}
