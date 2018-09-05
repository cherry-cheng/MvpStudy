package com.weizhan.superlook.di.module;

import com.weizhan.superlook.di.scope.PerActivity;
import com.weizhan.superlook.di.scope.PerFragment;
import com.weizhan.superlook.ui.bangumi.BangumiFragment;
import com.weizhan.superlook.ui.main.MainFragment;
import com.weizhan.superlook.ui.recommed.RecommendFragment;
import com.weizhan.superlook.ui.region.RegionFragment;
import com.weizhan.superlook.ui.test.fragment.NewsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiayiyang on 17/4/14.
 */

@Module
public class PageModule {

    //Test
    @Provides
    @PerActivity
    NewsFragment provideNewsFragment(){
        return new NewsFragment();
    }

    //main
    @Provides
    @PerActivity
    MainFragment provideMainFragment() {
        return new MainFragment();
    }

    @Provides
    @PerFragment
    RecommendFragment provideRecommendFragment() {
        return new RecommendFragment();
    }

    @Provides
    @PerFragment
    BangumiFragment provideBangumiFragment() {
        return new BangumiFragment();
    }

    @Provides
    @PerActivity
    RegionFragment provideRegionFragment() {
        return new RegionFragment();
    }
}
