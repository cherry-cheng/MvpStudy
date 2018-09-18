package com.weizhan.superlook.ui.play;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;
import com.weizhan.superlook.widget.adapter.CommonAdapter;
import com.weizhan.superlook.widget.adapter.VarietyAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;

/**
 * Created by Administrator on 2018/9/18.
 */

public class Play2Activity extends BaseActivity implements IBaseMvpActivity<PlayPresenter>, PlayContract.View {

    private IjkVideoView ijkVideoView;

    @Inject
    PlayPresenter mPresenter;
    @BindView(R.id.guessLike_recyclerView)
    RecyclerView guessLike_recyclerView;
    @BindView(R.id.iv_displayIntro)
    ImageView iv_displayIntro;
    @BindView(R.id.ll_display)
    LinearLayout ll_display;
    @BindView(R.id.seriesRecyclerView)
    RecyclerView seriesRecyclerView;

    private CommonAdapter mAdapter;
    private VarietyAdapter varietyAdapter;
    private static final int SPAN_COUNT = 2;

    private List<String> list2 = new ArrayList<String>();

    @Override
    protected void onPause() {
        super.onPause();
        ijkVideoView.pause();
    }

    @OnClick(R.id.iv_displayIntro)
    void onDisplayClick() {
        if (iv_displayIntro.isSelected()) {
            iv_displayIntro.setSelected(false);
            ll_display.setVisibility(View.GONE);
        } else {
            iv_displayIntro.setSelected(true);
            ll_display.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ijkVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkVideoView.release();
    }

    @Override
    public void onBackPressed() {
        if (!ijkVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public int getLayoutId() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        return R.layout.play2_activity;
    }

    @Override
    public void initInject() {
        App.getInstance().getActivityComponent().inject(this);
    }

    @Override
    public void initViewAndEvent() {
        ijkVideoView = findViewById(R.id.player);

        Intent intent = getIntent();

        if (intent != null) {
            StandardVideoController controller = new StandardVideoController(this);
            ijkVideoView.setPlayerConfig(new PlayerConfig.Builder()
                    .autoRotate()//自动旋转屏幕
//                    .enableCache()//启用边播边存
//                    .enableMediaCodec()//启动硬解码
//                    .usingSurfaceView()//使用SurfaceView
//                    .setCustomMediaPlayer(new ExoMediaPlayer(this))
//                    .setCustomMediaPlayer(new AndroidMediaPlayer(this))
                    .build());
//            ijkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
            ijkVideoView.setUrl(intent.getStringExtra("url"));
            ijkVideoView.setVideoController(controller);
            ijkVideoView.start();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        guessLike_recyclerView.setLayoutManager(layoutManager);
        guessLike_recyclerView.addItemDecoration(new GuessLike2ItemDecoration());
        guessLike_recyclerView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        mAdapter = new CommonAdapter(0, 99);
        mAdapter.register(AppRecommend1Show.Body.class, new GueLikeBodyItemViewBinder());
        mAdapter.setScrollSaveStrategyEnabled(true);
        guessLike_recyclerView.setAdapter(mAdapter);


        list2.addAll(Arrays.asList("全部类型", "喜剧", "动作", "爱情", "剧情", "科幻", "伦理", "悬疑", "惊悚", "其他"));
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 1);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        seriesRecyclerView.setLayoutManager(layoutManager1);
        seriesRecyclerView.addItemDecoration(new SeriesItemDecoration());
        varietyAdapter = new VarietyAdapter(list2, this);
        seriesRecyclerView.setAdapter(varietyAdapter);

        //监听单选
        varietyAdapter.setOnItemSingleSelectListener(new VarietyAdapter.OnItemSingleSelectListener() {

            @Override
            public void onSelected(int itemPosition, boolean isSelected) {
                Toast.makeText(Play2Activity.this, "selectedPosition:" + itemPosition  +" == "+ varietyAdapter.getSingleSelectedPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public PlayPresenter getPresenter() {
        return mPresenter;
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
