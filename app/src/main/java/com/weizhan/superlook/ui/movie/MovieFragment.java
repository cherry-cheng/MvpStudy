package com.weizhan.superlook.ui.movie;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.common.base.BaseMvpFragment;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.movie.AppMovieShow;
import com.weizhan.superlook.ui.movie.viewbinder.MovieBodyItemViewBinder;
import com.weizhan.superlook.ui.movie.viewbinder.MovieFooterItemViewBinder;
import com.weizhan.superlook.ui.movie.viewbinder.MoviePartitionItemViewBinder;
import com.weizhan.superlook.widget.adapter.CommonAdapter;

import butterknife.BindView;
import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/5.
 */

public class MovieFragment extends BaseMvpFragment<MoviePresenter> implements MovieContract.View {

    public static final String TAG = MovieFragment.class.getSimpleName();

    private static final int SPAN_COUNT = 3;

    @BindView(R.id.rv_region)
    RecyclerView mRecyclerView;

    private CommonAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
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
                return item instanceof AppMovieShow.Body ? 1 : SPAN_COUNT;
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new MovieIndexItemDecoration());
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        mAdapter = new CommonAdapter();
        mAdapter.register(AppMovieShow.Partition.class, new MoviePartitionItemViewBinder());
        mAdapter.register(AppMovieShow.Body.class, new MovieBodyItemViewBinder());
        mAdapter.register(MovieFooterItemViewBinder.MovieFooter.class, new MovieFooterItemViewBinder());
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
