package com.weizhan.superlook.ui.region.series;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.series.AppSeriesShow;
import com.weizhan.superlook.ui.series.SeriesIndexItemDecoration;
import com.weizhan.superlook.ui.series.viewbinder.SeriesBodyItemViewBinder;
import com.weizhan.superlook.ui.series.viewbinder.SeriesFooterItemViewBinder;
import com.weizhan.superlook.ui.series.viewbinder.SeriesPartitionItemViewBinder;
import com.weizhan.superlook.widget.adapter.CommonAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/11.
 */

public class SeriesRActivity extends BaseActivity implements IBaseMvpActivity<SeriesRPresenter>, SeriesRContract.View {
    @Inject
    SeriesRPresenter mPresenter;
    @BindView(R.id.recyclerViewContent)
    RecyclerView recyclerViewContent;
    private CommonAdapter commonAdapter;
    private static final int SPAN_COUNT = 2;
    @Override
    public int getLayoutId() {
        return R.layout.activity_seriesr;
    }

    @OnClick(R.id.back_iv)
    void onBack() {
        finish();
    }

    @Override
    public void initInject() {
        App.getInstance().getActivityComponent().inject(this);
    }

    @Override
    public void initViewAndEvent() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = commonAdapter.getItems().get(position);
                return item instanceof AppSeriesShow.Body ? 1 : SPAN_COUNT;
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        recyclerViewContent.setLayoutManager(layoutManager);
        recyclerViewContent.addItemDecoration(new SeriesIndexItemDecoration());
        recyclerViewContent.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        commonAdapter = new CommonAdapter(0, 1);
        commonAdapter.register(AppSeriesShow.Body.class, new SeriesBodyItemViewBinder());
        commonAdapter.setScrollSaveStrategyEnabled(true);
        recyclerViewContent.setAdapter(commonAdapter);
    }

    @Override
    public SeriesRPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
//        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDataUpdated(Items items) {
        commonAdapter.setItems(items);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadFailed() {
        commonAdapter.showLoadFailed();
    }
}
