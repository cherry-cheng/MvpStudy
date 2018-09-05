package com.weizhan.superlook.di.component;

import com.weizhan.superlook.di.module.FragmentModule;
import com.weizhan.superlook.di.module.PageModule;
import com.weizhan.superlook.di.scope.PerFragment;
import com.weizhan.superlook.ui.bangumi.BangumiFragment;
import com.weizhan.superlook.ui.main.MainFragment;
import com.weizhan.superlook.ui.recommed.RecommendFragment;
import com.weizhan.superlook.ui.region.RegionFragment;
import com.weizhan.superlook.ui.test.fragment.NewsFragment;
import com.weizhan.superlook.ui.test.fragment.NewsPageFragment;
import com.weizhan.superlook.ui.test.fragment.NewsPageFragment2;

import dagger.Component;

/**
 * Created by jiayiyang on 17/4/14.
 */

@Component(dependencies = ApiComponent.class, modules = {FragmentModule.class, PageModule.class})
@PerFragment
public interface FragmentComponent {

    void inject(NewsFragment newsFragment);

    void inject(NewsPageFragment newsPageFragment);

    void inject(NewsPageFragment2 newsPageFragment2);

    void inject(MainFragment mainFragment);

    void inject(BangumiFragment bangumiFragment);

    void inject(RecommendFragment recommendFragment);

    void inject(RegionFragment regionFragment);
}
