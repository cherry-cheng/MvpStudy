package com.weizhan.superlook.ui.search.result;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.common.base.BaseFragment;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.TabSelectedEvent;
import com.weizhan.superlook.ui.main.MainActivity;
import com.weizhan.superlook.ui.search.result.all.AllSearchFragment;
import com.weizhan.superlook.ui.search.result.movie.MovieSFragment;
import com.weizhan.superlook.ui.search.result.series.SeriesSFragment;
import com.weizhan.superlook.ui.search.result.variety.VarietySFragment;
import com.weizhan.superlook.ui.test.fragment.PlaceHolderFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/10.
 */

public class SearchResultFragment extends BaseFragment {
    private SearchResultFragment.ResultPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;

    @BindView(R.id.tab_layout)
    XTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @Inject
    AllSearchFragment allSearchFragment;
    @Inject
    MovieSFragment movieSFragment;
    @Inject
    SeriesSFragment seriesSFragment;
    @Inject
    VarietySFragment varietySFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_searchresult;
    }

    @Override
    protected void initInject() {
        App.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {
        mTitles = getResources().getStringArray(R.array.search_sections);
        initChildFragment();
        adapter = new SearchResultFragment.ResultPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).select();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TabSelectedEvent event){
        if(event.getPosition() == MainActivity.FIRST){

        }
    }

    private void initChildFragment() {
        mFragments.add(allSearchFragment);
        mFragments.add(movieSFragment);
        mFragments.add(seriesSFragment);
        mFragments.add(varietySFragment);
    }

    private class ResultPagerAdapter extends FragmentPagerAdapter {

        ResultPagerAdapter(FragmentManager fm) {
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
}
