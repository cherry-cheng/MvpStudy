package com.weizhan.superlook.di.module;

import com.weizhan.superlook.di.scope.PerActivity;
import com.weizhan.superlook.di.scope.PerFragment;
import com.weizhan.superlook.ui.bangumi.BangumiFragment;
import com.weizhan.superlook.ui.main.MainFragment;
import com.weizhan.superlook.ui.movie.MovieFragment;
import com.weizhan.superlook.ui.recommend1.Recommend1Fragment;
import com.weizhan.superlook.ui.region.RegionFragment;
import com.weizhan.superlook.ui.search.home.SearchHomeFragment;
import com.weizhan.superlook.ui.series.SeriesFragment;
import com.weizhan.superlook.ui.test.fragment.NewsFragment;
import com.weizhan.superlook.ui.variety.VarietyFragment;

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
    BangumiFragment provideBangumiFragment() {
        return new BangumiFragment();
    }

    @Provides
    @PerActivity
    RegionFragment provideRegionFragment() {
        return new RegionFragment();
    }

    @Provides
    @PerActivity
    SearchHomeFragment provideSearchHomeFragment() {
        return new SearchHomeFragment();
    }

    @Provides
    @PerFragment
    Recommend1Fragment provideRecommend1Fragment() {
        return new Recommend1Fragment();
    }

    @Provides
    @PerFragment
    SeriesFragment provideSeriesFragment() {
        return new SeriesFragment();
    }

    @Provides
    @PerFragment
    VarietyFragment provideVarietyFragment() {
        return new VarietyFragment();
    }

    @Provides
    @PerFragment
    MovieFragment provideMovieFragment() {
        return new MovieFragment();
    }
}
