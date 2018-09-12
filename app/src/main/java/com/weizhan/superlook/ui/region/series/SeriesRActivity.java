package com.weizhan.superlook.ui.region.series;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.series.AppSeriesShow;
import com.weizhan.superlook.ui.series.SeriesIndexItemDecoration;
import com.weizhan.superlook.ui.series.viewbinder.SeriesBodyItemViewBinder;
import com.weizhan.superlook.widget.adapter.CommonAdapter;
import com.weizhan.superlook.widget.adapter.EasyAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    private CommonAdapter commonAdapter;
    private static final int SPAN_COUNT = 2;
    private List<String> list = new ArrayList<String>();
    private List<String> list1 = new ArrayList<String>();
    private EasyAdapter easyAdapter1;
    private EasyAdapter easyAdapter2;
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


        //title中的两个recyclerview
        list.addAll(Arrays.asList("综合排序", "热播", "好评", "新上线"));
        list1.addAll(Arrays.asList("全部地区", "国产", "韩国", "其他"));
        easyAdapter1 = new EasyAdapter(list, this);
        easyAdapter2 = new EasyAdapter(list1, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //把列表设置成水平滚动
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL); //把列表设置成水平滚动
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView1.setAdapter(easyAdapter1);
        recyclerView2.setAdapter(easyAdapter2);

        //监听单选
        easyAdapter1.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() {

            @Override
            public void onSelected(int itemPosition, boolean isSelected) {
                Toast.makeText(SeriesRActivity.this, "selectedPosition:" + itemPosition  +" == "+ easyAdapter1.getSingleSelectedPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        //监听单选
        easyAdapter2.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() {

            @Override
            public void onSelected(int itemPosition, boolean isSelected) {
                Toast.makeText(SeriesRActivity.this, "selectedPosition:" + itemPosition  +" == "+ easyAdapter2.getSingleSelectedPosition(), Toast.LENGTH_SHORT).show();
            }
        });

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
