package com.weizhan.superlook.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.ToggleDrawerEvent;
import com.weizhan.superlook.ui.region.RegionFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/9/7.
 */

public class SearchActivity extends BaseActivity implements IBaseMvpActivity<SearchPresenter>, SearchContract.View {

    @Inject
    SearchPresenter mPresenter;
    @Inject
    RegionFragment searchHomeFragment;
    @BindView(R.id.search_container)
    FrameLayout mFrameLayout;

    private SupportFragment[] mFragments = new SupportFragment[4];

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initInject() {
        App.getInstance().getActivityComponent().inject(this);
    }

    @Override
    public SearchPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initViewAndEvent() {
//        showHideFragment(searchHomeFragment);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRootFragment(R.id.search_container, searchHomeFragment);
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

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finish();
        }
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ToggleDrawerEvent event) {

    }
}
