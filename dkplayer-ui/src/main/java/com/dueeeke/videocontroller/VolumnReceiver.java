package com.dueeeke.videocontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/9/17.
 */

public class VolumnReceiver extends BroadcastReceiver {
    private ImageView vol;
    AudioManager am = null;

    public VolumnReceiver(ImageView pow) {
        this.vol = pow;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction())) {
            am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            Log.i("cyh11", "if ==== am.vol = " + am.getStreamVolume(AudioManager.STREAM_MUSIC));
            if (am.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
                vol.setSelected(false);
            } else {
                vol.setSelected(true);
            }
        }
    }
}
