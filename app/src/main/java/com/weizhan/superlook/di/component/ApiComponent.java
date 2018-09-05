package com.weizhan.superlook.di.component;

import com.weizhan.superlook.di.module.ApiModule;
import com.weizhan.superlook.di.scope.GlobalApis;
import com.weizhan.superlook.model.api.AppApis;
import com.weizhan.superlook.model.api.BangumiApis;
import com.weizhan.superlook.model.api.RecommendApis;
import com.weizhan.superlook.model.api.RegionApis;
import com.weizhan.superlook.model.api.WeChatApis;
import com.weizhan.superlook.model.api.ZhihuApis;
import com.common.app.AppComponent;

import dagger.Component;

/**
 * Created by Android_ZzT on 17/6/15.
 */

@Component(dependencies = AppComponent.class, modules = ApiModule.class)
@GlobalApis
public interface ApiComponent {

    AppApis appApis();

    RecommendApis recommendApis();

    BangumiApis biliBiliApis();

    RegionApis regionApis();

    //Test
    ZhihuApis zhihuApis();

    WeChatApis weChatApis();
}
