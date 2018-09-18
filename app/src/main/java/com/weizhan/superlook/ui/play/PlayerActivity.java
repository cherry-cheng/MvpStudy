package com.weizhan.superlook.ui.play;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.widget.adapter.CommonAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PlayerActivity extends BaseActivity implements IBaseMvpActivity<PlayPresenter>, PlayContract.View {

    private IjkVideoView ijkVideoView;

    @Inject
    PlayPresenter mPresenter;
    @BindView(R.id.guessLike_recyclerView)
    RecyclerView guessLike_recyclerView;
    @BindView(R.id.iv_displayIntro)
    ImageView iv_displayIntro;
    @BindView(R.id.ll_display)
    LinearLayout ll_display;

    private CommonAdapter mAdapter;

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
    }

    @Override
    public PlayPresenter getPresenter() {
        return mPresenter;
    }
}
