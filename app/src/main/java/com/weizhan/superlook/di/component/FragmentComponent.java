package com.weizhan.superlook.di.component;

import com.weizhan.superlook.di.module.FragmentModule;
import com.weizhan.superlook.di.module.PageModule;
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
import com.weizhan.superlook.ui.test.fragment.NewsPageFragment;
import com.weizhan.superlook.ui.test.fragment.NewsPageFragment2;
import com.weizhan.superlook.ui.variety.VarietyFragment;

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

    void inject(MainHistoryFragment mainHistoryFragment);

    void inject(SearchResultFragment searchResultFragment);

    void inject(BangumiFragment bangumiFragment);

    void inject(Recommend1Fragment recommend1Fragment);

    void inject(CollectFragment collectFragment);

    void inject(OffFragment offFragment);

    void inject(PastFragment pastFragment);

    void inject(AllSearchFragment allSearchFragment);

    void inject(MovieSFragment movieSFragment);

    void inject(VarietySFragment varietySFragment);

    void inject(SeriesSFragment seriesSFragment);

    void inject(RegionFragment regionFragment);

    void inject(MineFragment mineFragment);

    void inject(SearchHomeFragment searchHomeFragment);

    void inject(SeriesFragment seriesFragment);

    void inject(MovieFragment movieFragment);

    void inject(VarietyFragment varietyFragment);
}
