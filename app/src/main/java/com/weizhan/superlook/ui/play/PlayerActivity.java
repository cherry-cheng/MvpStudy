package com.weizhan.superlook.ui.play;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.play.TestBean;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;
import com.weizhan.superlook.widget.adapter.CommonAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.drakeet.multitype.Items;

public class PlayerActivity extends BaseActivity implements IBaseMvpActivity<PlayPresenter>, PlayContract.View {

    private IjkVideoView ijkVideoView;

    @Inject
    PlayPresenter mPresenter;
    @BindView(R.id.guessLike_recyclerView)
    RecyclerView guessLike_recyclerView;

    private CommonAdapter mAdapter;
    private static final int SPAN_COUNT = 3;

    private List<String> list1 = new ArrayList<String>();
    private List<String> list2 = new ArrayList<String>();

    @Override
    protected void onPause() {
        super.onPause();
        ijkVideoView.pause();
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
        return R.layout.activity_player;
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
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = mAdapter.getItems().get(position);
                return item instanceof AppRecommend1Show.Body ? 1 : SPAN_COUNT;
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        guessLike_recyclerView.setLayoutManager(layoutManager);
        guessLike_recyclerView.addItemDecoration(new GuessLikeItemDecoration());
        guessLike_recyclerView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        mAdapter = new CommonAdapter(0, 99);
        mAdapter.register(AppRecommend1Show.Partition.class, new PlayTitleItemViewBinder());
        mAdapter.register(AppRecommend1Show.Body.class, new GueLikeBodyItemViewBinder());
        mAdapter.register(TestBean.class, new GuessTitleViewBinder());
        mAdapter.setScrollSaveStrategyEnabled(true);
        guessLike_recyclerView.setAdapter(mAdapter);
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
