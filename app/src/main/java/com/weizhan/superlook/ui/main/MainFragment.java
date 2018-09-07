package com.weizhan.superlook.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.TabSelectedEvent;
import com.weizhan.superlook.ui.bangumi.BangumiFragment;
import com.weizhan.superlook.ui.movie.MovieFragment;
import com.weizhan.superlook.ui.recommend1.Recommend1Fragment;
import com.weizhan.superlook.ui.search.SearchActivity;
import com.weizhan.superlook.ui.series.SeriesFragment;
import com.common.base.BaseFragment;
import com.weizhan.superlook.ui.variety.VarietyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页主Fragment
 * Created by jiayiyang on 17/4/14.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    XTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;

    @Inject
    Recommend1Fragment mRecommend1Fragment;
    @Inject
    BangumiFragment mBangumiFragment;
    @Inject
    SeriesFragment mSeriesFragment;
    @Inject
    MovieFragment mMovieFragment;
    @Inject
    VarietyFragment mVarietyFragment;

    private MainPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initInject() {
        App.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {
        mTitles = getResources().getStringArray(R.array.home_sections);
        initChildFragment();
        adapter = new MainPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).select();
    }

    private void initChildFragment() {
        mFragments.add(mRecommend1Fragment);
        mFragments.add(mSeriesFragment);
        mFragments.add(mMovieFragment);
        mFragments.add(mVarietyFragment);
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @OnClick(R.id.rl_search)
    void goSearch() {
        SearchActivity.startActivity(getContext());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TabSelectedEvent event){
        if(event.getPosition() == MainActivity.FIRST){

        }
    }

}
