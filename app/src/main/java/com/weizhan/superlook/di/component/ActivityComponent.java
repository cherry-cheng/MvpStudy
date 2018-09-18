package com.weizhan.superlook.di.component;

import com.weizhan.superlook.di.module.ActivityModule;
import com.weizhan.superlook.di.module.PageModule;
import com.weizhan.superlook.di.scope.PerActivity;
import com.weizhan.superlook.ui.main.MainActivity;
import com.weizhan.superlook.ui.play.Play1Activity;
import com.weizhan.superlook.ui.play.Play2Activity;
import com.weizhan.superlook.ui.play.PlayerActivity;
import com.weizhan.superlook.ui.region.series.SeriesRActivity;
import com.weizhan.superlook.ui.search.SearchActivity;
import com.weizhan.superlook.ui.search.need.NeedMovieActivity;

import dagger.Component;

/**
 * Created by jiayiyang on 17/3/23.
 */

@Component(dependencies = {ApiComponent.class}, modules = {ActivityModule.class, PageModule.class})
@PerActivity
public interface ActivityComponent {
    //superlook
    void inject(MainActivity mainActivity);
    void inject(SearchActivity searchActivity);
    void inject(NeedMovieActivity needMovieActivity);
    void inject(SeriesRActivity seriesRActivity);
    void inject(PlayerActivity playerActivity);
    void inject(Play1Activity play1Activity);
    void inject(Play2Activity play2Activity);

    //Test

}
