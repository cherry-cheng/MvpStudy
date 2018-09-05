package com.weizhan.superlook.util;

import android.text.TextUtils;

import com.common.util.Utils;
import com.weizhan.superlook.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Android_ZzT on 17/7/9.
 */

public class ResourceManager {

    private static Map<String, Integer> regionResMap = new HashMap<>();

    static {
        regionResMap.put("番剧区", R.mipmap.ic_category_unknown);
        regionResMap.put("动画区", R.mipmap.ic_category_unknown);
        regionResMap.put("国创区", R.mipmap.ic_category_unknown);
        regionResMap.put("音乐区", R.mipmap.ic_category_unknown);
        regionResMap.put("舞蹈区", R.mipmap.ic_category_unknown);
        regionResMap.put("游戏区", R.mipmap.ic_category_unknown);
        regionResMap.put("鬼畜区", R.mipmap.ic_category_unknown);
        regionResMap.put("生活区", R.mipmap.ic_category_unknown);
        regionResMap.put("科技区", R.mipmap.ic_category_unknown);
        regionResMap.put("活动中心", R.mipmap.ic_category_unknown);
        regionResMap.put("时尚区", R.mipmap.ic_category_unknown);
        regionResMap.put("广告区", R.mipmap.ic_category_unknown);
        regionResMap.put("娱乐区", R.mipmap.ic_category_unknown);
        regionResMap.put("电视剧区", R.mipmap.ic_category_unknown);
        regionResMap.put("电影区", R.mipmap.ic_category_unknown);

    }

    public static int getRegionIconByTitle(String title) {
        return regionResMap.get(title);
    }

    public static int getRegionIconByParam(String param) {
        String name;
        if (TextUtils.equals("177", param)) {//纪录片
            return R.mipmap.ic_category_unknown;
        } else if (TextUtils.equals("subarea", param)) {//活动中心
            return R.mipmap.ic_category_unknown;
        } else {
            name = "ic_category_t" + param;
        }
        return Utils.getContext().getResources().getIdentifier(name, "mipmap", Utils.getContext().getPackageName());
    }
}
