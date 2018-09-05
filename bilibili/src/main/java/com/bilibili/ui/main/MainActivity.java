package com.bilibili.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bilibili.App;
import com.bilibili.R;
import com.bilibili.model.event.ToggleDrawerEvent;
import com.bilibili.ui.region.RegionFragment;
import com.bilibili.util.BottomNavigationViewHelper;
import com.bilibili.ui.test.fragment.PlaceHolderFragment;
import com.common.base.IBaseMvpActivity;
import com.common.base.BaseActivity;
import com.common.util.StatusBarUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity implements IBaseMvpActivity<MainPresenter>, MainContract.View {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    @Inject
    MainPresenter mPresenter;
    @Inject
    MainFragment mainFragment;
    @Inject
    RegionFragment regionFragment;
    @BindView(R.id.entrance_bar)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.main_container)
    FrameLayout mFrameLayout;

    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    public void initInject() {
        App.getInstance().getActivityComponent().inject(this);
    }

    @Override
    public MainPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewAndEvent() {
//        StatusBarUtil.setColorForDrawerLayout(this, getResources().getColor(R.color.colorPrimary), mFrameLayout);
        bottomNavigationView.setItemIconTintList(null);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        showHideFragment(mFragments[FIRST]);
                        break;
                    case R.id.menu_item_category:
                        showHideFragment(mFragments[SECOND]);
                        break;
                    case R.id.menu_item_history:
                        showHideFragment(mFragments[THIRD]);
                        break;
                    case R.id.menu_item_mine:
                        showHideFragment(mFragments[FOURTH]);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments[FIRST] = mainFragment;
        mFragments[SECOND] = regionFragment;
        mFragments[THIRD] = new PlaceHolderFragment();
        mFragments[FOURTH] = new PlaceHolderFragment();
        loadMultipleRootFragment(R.id.main_container, 0,
                mFragments[FIRST],
                mFragments[SECOND],
                mFragments[THIRD],
                mFragments[FOURTH]);
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
