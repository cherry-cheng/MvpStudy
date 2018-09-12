package com.weizhan.superlook.ui.region.series;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
    @BindView(R.id.recyclerView3)
    RecyclerView recyclerView3;
    private CommonAdapter commonAdapter;
    private static final int SPAN_COUNT = 2;

    private List<String> list = new ArrayList<String>();
    private List<String> list1 = new ArrayList<String>();
    private List<String> list2 = new ArrayList<String>();
    private List<String> list3 = new ArrayList<String>();
    private int type = 0;

    private EasyAdapter easyAdapter1;
    private EasyAdapter easyAdapter2;
    private EasyAdapter easyAdapter3;
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
        list2.addAll(Arrays.asList("全部类型", "喜剧", "动作", "爱情", "剧情", "科幻", "伦理", "悬疑", "惊悚", "其他"));
        easyAdapter1 = new EasyAdapter(list, this);
        easyAdapter2 = new EasyAdapter(list1, this);
        easyAdapter3 = new EasyAdapter(list3, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //把列表设置成水平滚动
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL); //把列表设置成水平滚动
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL); //把列表设置成水平滚动
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        recyclerView1.setAdapter(easyAdapter1);
        recyclerView2.setAdapter(easyAdapter2);
        recyclerView3.setAdapter(easyAdapter3);

        type = getIntent().getIntExtra("which", 0);
        Log.i("cyh111", "type11 -----------" + type);
        if (type == 1) {
            recyclerView3.setVisibility(View.VISIBLE);
        } else {
            recyclerView3.setVisibility(View.GONE);
        }

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

        //监听单选
        easyAdapter3.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() {

            @Override
            public void onSelected(int itemPosition, boolean isSelected) {
                Toast.makeText(SeriesRActivity.this, "selectedPosition:" + itemPosition  +" == "+ easyAdapter3.getSingleSelectedPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        mPresenter.LoadAccordParams(type);

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
    public void onDataResponse(int type) {
        if (type == 1) {
            easyAdapter3.setData(list2);
        }
    }

    @Override
    public void showLoadFailed() {
        commonAdapter.showLoadFailed();
    }
}
