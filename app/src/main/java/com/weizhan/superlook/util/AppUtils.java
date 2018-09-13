package com.weizhan.superlook.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.weizhan.superlook.App;

import java.io.File;

/**
 * Created by liujun26 on 2017/5/26.
 */

public class AppUtils {
    public static boolean isExternalStorageAllowed = true;
    public static boolean ExistSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            return true;
        } else {
            return false;
        }
    }

    public static File getAvailableCacheDir() {
            if (isExternalStorageAllowed && ExistSDCard())
                return App.getInstance().getExternalCacheDir();
            else
                return App.getInstance().getCacheDir();
    }

    public static File getInternalCacheDir() {
        return App.getInstance().getCacheDir();
    }

    public static File getExternalCacheDir() {
        return App.getInstance().getExternalCacheDir();
    }

    public static String getAvailableCacheDirPath() {
        String result = null;
        if (getAvailableCacheDir() == null) {
            result = getInternalCacheDir().getAbsolutePath();
        } else {
            result = getAvailableCacheDir().getAbsolutePath();
        }
        if (result==null || result.trim().equals("")) {
            result = App.getInstance().getCacheDir() + File.separator + "bitmap-cache";
        }
        return result;
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

}
