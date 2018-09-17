package com.weizhan.superlook.ui.play;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.common.base.BaseActivity;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.weizhan.superlook.R;

public class PlayerActivity extends AppCompatActivity {

    private IjkVideoView ijkVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_player);
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
}
