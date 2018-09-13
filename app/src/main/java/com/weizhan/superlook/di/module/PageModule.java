package com.weizhan.superlook.di.module;

import com.weizhan.superlook.di.scope.PerActivity;
import com.weizhan.superlook.di.scope.PerFragment;
import com.weizhan.superlook.ui.bangumi.BangumiFragment;
import com.weizhan.superlook.ui.history.MainHistoryFragment;
import com.weizhan.superlook.ui.history.collect.CollectFragment;
import com.weizhan.superlook.ui.history.off.OffFragment;
import com.weizhan.superlook.ui.history.past.PastFragment;
import com.weizhan.superlook.ui.main.MainFragment;
import com.weizhan.superlook.ui.mine.MineFragment;
import com.weizhan.superlook.ui.movie.MovieFragment;
import com.weizhan.superlook.ui.recommend1.Recommend1Fragment;
import com.weizhan.superlook.ui.region.RegionFragment;
import com.weizhan.superlook.ui.search.home.SearchHomeFragment;
import com.weizhan.superlook.ui.search.result.SearchResultFragment;
import com.weizhan.superlook.ui.search.result.all.AllSearchFragment;
import com.weizhan.superlook.ui.search.result.movie.MovieSFragment;
import com.weizhan.superlook.ui.search.result.series.SeriesSFragment;
import com.weizhan.superlook.ui.search.result.variety.VarietySFragment;
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

    //history
    @Provides
    @PerActivity
    MainHistoryFragment provideMainHistoryFragment() {
        return new MainHistoryFragment();
    }

    //main
    @Provides
    @PerActivity
    SearchResultFragment provideSearchResultFragment() {
        return new SearchResultFragment();
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
    MineFragment provideMineFragment() {
        return new MineFragment();
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
    CollectFragment provideCollectFragment() {
        return new CollectFragment();
    }

    @Provides
    @PerFragment
    OffFragment provideOffFragment() {
        return new OffFragment();
    }

    @Provides
    @PerFragment
    PastFragment providePastFragment() {
        return new PastFragment();
    }

    @Provides
    @PerFragment
    AllSearchFragment provideAllSearchFragmentFragment() {
        return new AllSearchFragment();
    }

    @Provides
    @PerFragment
    MovieSFragment provideMovieSFragment() {
        return new MovieSFragment();
    }

    @Provides
    @PerFragment
    SeriesSFragment provideSeriesSFragment() {
        return new SeriesSFragment();
    }

    @Provides
    @PerFragment
    VarietySFragment provideVarietySFragment() {
        return new VarietySFragment();
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
