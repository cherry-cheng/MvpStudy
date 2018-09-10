package com.weizhan.superlook.ui.recommend1;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.common.base.BaseMvpFragment;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;
import com.weizhan.superlook.ui.recommend1.viewbinder.Recommend1BMItemViewBinder;
import com.weizhan.superlook.ui.recommend1.viewbinder.Recommend1BannerItemViewBinder;
import com.weizhan.superlook.ui.recommend1.viewbinder.Recommend1BodyItemViewBinder;
import com.weizhan.superlook.ui.recommend1.viewbinder.Recommend1FooterItemViewBinder;
import com.weizhan.superlook.ui.recommend1.viewbinder.Recommend1PartitionItemViewBinder;
import com.weizhan.superlook.widget.adapter.CommonAdapter;

import butterknife.BindView;
import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/5.
 */

public class Recommend1Fragment extends BaseMvpFragment<Recommend1Presenter> implements Recommend1Contract.View {

    public static final String TAG = Recommend1Fragment.class.getSimpleName();

    private static final int SPAN_COUNT = 3;
    private static final int SPAN_COUNT3 = 6;

    @BindView(R.id.rv_region)
    RecyclerView mRecyclerView;

    private CommonAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend1;
    }

    @Override
    protected void initInject() {
        App.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT3);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = mAdapter.getItems().get(position);
                if (item instanceof AppRecommend1Show.BodyMovie) {
                    return 2;
                }
                return item instanceof AppRecommend1Show.Body ? SPAN_COUNT : SPAN_COUNT3;
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new Recommend1IndexItemDecoration());
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        mAdapter = new CommonAdapter();
//        mAdapter.register(SeriesHeaderItemViewBinder.Recommend1Header.class, new SeriesHeaderItemViewBinder());
        mAdapter.register(AppRecommend1Show.Banner.class, new Recommend1BannerItemViewBinder());
        mAdapter.register(AppRecommend1Show.Partition.class, new Recommend1PartitionItemViewBinder());
        mAdapter.register(AppRecommend1Show.Body.class, new Recommend1BodyItemViewBinder());
        mAdapter.register(AppRecommend1Show.BodyMovie.class, new Recommend1BMItemViewBinder());
        mAdapter.register(Recommend1FooterItemViewBinder.Recommend1Footer.class, new Recommend1FooterItemViewBinder());
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
