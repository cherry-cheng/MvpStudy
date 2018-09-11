package com.weizhan.superlook.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.search.SearchKey;
import com.weizhan.superlook.model.event.ClickMessage;
import com.weizhan.superlook.model.event.JumpActivity;
import com.weizhan.superlook.ui.search.home.SearchHomeFragment;
import com.weizhan.superlook.ui.search.need.NeedMovieActivity;
import com.weizhan.superlook.ui.search.result.SearchResultFragment;
import com.weizhan.superlook.util.RealmHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/9/7.
 */

public class SearchActivity extends BaseActivity implements IBaseMvpActivity<SearchPresenter>, SearchContract.View {

    @Inject
    SearchPresenter mPresenter;
    @Inject
    SearchHomeFragment searchHomeFragment;
    @Inject
    SearchResultFragment searchResultFragment;

    @BindView(R.id.search_container)
    FrameLayout mFrameLayout;
    @BindView(R.id.back_iv)
    ImageView back_iv;
    @BindView(R.id.icon_search)
    ImageView icon_search;
    @BindView(R.id.et_input)
    EditText et_input;
    @BindView(R.id.cancel_iv)
    ImageView cancel_iv;

    private SupportFragment[] mFragments = new SupportFragment[4];

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.back_iv)
    void onBack() {
        finish();
    }

    @OnClick(R.id.cancel_iv)
    void Cancel() {
        cancel_iv.setVisibility(View.GONE);
        icon_search.setVisibility(View.VISIBLE);
//        showHideFragment(searchHomeFragment, searchResultFragment);
//        replaceFragment(searchHomeFragment, false);
/*        if (searchHomeFragment.isHidden()) {
            showHideFragment(searchHomeFragment);
        } else {
            start(searchHomeFragment);
        }*/
        et_input.setText("");

    }

    @OnClick(R.id.icon_search)
    void onSearch() {
        String keyword = et_input.getText().toString();
        keyword = TextUtils.isEmpty(keyword) ? et_input.getHint().toString() : keyword;
        //hint赋值到内容
        et_input.setText(keyword);
        et_input.setSelection(et_input.getText().length());
        //收起软键盘
        hideKeyboard(et_input);
        //跳入搜索结果界面
//        replaceFragment(searchResultFragment, false);
//        showHideFragment(searchResultFragment, searchHomeFragment);
        if (searchResultFragment.isVisible()) {
//            showHideFragment(searchResultFragment);
            //刷新当前的fragment
            searchResultFragment.onAttach(this);
        } else {
            start(searchResultFragment);
        }
        if (TextUtils.isEmpty(keyword.trim())) {
            //如果输入空，不去请求服务器,直接显示缺省
//            replaceFragment(searchResultFragment, false);
//            showHideFragment(searchResultFragment, searchHomeFragment);
//            start(searchResultFragment);
        } else {
            getSearchResult(keyword);
        }
    }

    private void GoSearchAction(String hotword) {
        et_input.setText(hotword);
        et_input.setSelection(et_input.getText().length());
        //收起软键盘
        hideKeyboard(et_input);
        //跳入搜索结果界面
        if (searchResultFragment.isVisible()) {
            //刷新当前的fragment
            searchResultFragment.onAttach(this);
        } else {
            start(searchResultFragment);
        }
        getSearchResult(hotword);
    }

    private void getSearchResult(String keyword) {
        //保存搜索记录到本地
        SearchKey search = new SearchKey(keyword, System.currentTimeMillis());
        RealmHelper.getInstance().insertSearchHistory(search);
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
        et_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard(et_input);
                    onSearch();
                    return true;
                }
                return false;
            }
        });

        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //输入过程的监听
                if (!TextUtils.isEmpty(charSequence)) {
                    cancel_iv.setVisibility(View.VISIBLE);
                    icon_search.setVisibility(View.GONE);
                } else {
                    cancel_iv.setVisibility(View.GONE);
                    icon_search.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ClickMessage clickMessage) {
        Log.i("cyh11", "clickMessage = " + clickMessage.getSearchString());
        GoSearchAction(clickMessage.getSearchString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent1(JumpActivity jumpActivity) {
        startActivity(new Intent(this, NeedMovieActivity.class));
    }
}
