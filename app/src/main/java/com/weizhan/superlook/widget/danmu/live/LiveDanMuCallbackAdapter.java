package com.weizhan.superlook.widget.danmu.live;

import com.weizhan.superlook.widget.danmu.live.entity.DanMuMSGEntity;
import com.weizhan.superlook.widget.danmu.live.entity.LiveEntity;
import com.weizhan.superlook.widget.danmu.live.entity.PreparingEntity;
import com.weizhan.superlook.widget.danmu.live.entity.RoomAdminsEntity;
import com.weizhan.superlook.widget.danmu.live.entity.SendGiftEntity;
import com.weizhan.superlook.widget.danmu.live.entity.SysGiftEntity;
import com.weizhan.superlook.widget.danmu.live.entity.SysMSGEntity;
import com.weizhan.superlook.widget.danmu.live.entity.WelcomeEntity;
import com.weizhan.superlook.widget.danmu.live.entity.WelcomeGuardEntity;

/**
 * Abstract class implement from ILiveDanMuCallback.
 * Created by czp on 17-6-6.
 */
public abstract class LiveDanMuCallbackAdapter implements ILiveDanMuCallback {
    @Override
    public void onConnect() {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onOnlineCountPackage(int onlineCount) {

    }

    @Override
    public void onDanMuMSGPackage(DanMuMSGEntity danMuMSGEntity) {

    }

    @Override
    public void onSysMSGPackage(SysMSGEntity sysMSGEntity) {

    }

    @Override
    public void onSendGiftPackage(SendGiftEntity sendGiftEntity) {

    }

    @Override
    public void onSysGiftPackage(SysGiftEntity sysGiftEntity) {

    }

    @Override
    public void onWelcomePackage(WelcomeEntity welcomeEntity) {

    }

    @Override
    public void onWelcomeGuardPackage(WelcomeGuardEntity welcomeGuardEntity) {

    }

    @Override
    public void onLivePackage(LiveEntity liveEntity) {

    }

    @Override
    public void onPreparingPackage(PreparingEntity preparingEntity) {

    }

    @Override
    public void onRoomAdminsPackage(RoomAdminsEntity roomAdminsEntity) {

    }
}
