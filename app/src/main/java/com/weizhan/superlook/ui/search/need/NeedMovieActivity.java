package com.weizhan.superlook.ui.search.need;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.common.base.BaseActivity;
import com.common.base.IBaseMvpActivity;
import com.common.util.ToastUtils;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.ClickMessage;
import com.weizhan.superlook.ui.main.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/7.
 */

public class NeedMovieActivity extends BaseActivity implements IBaseMvpActivity<NeedMoviePresenter>, NeedMovieContract.View {

    @Inject
    NeedMoviePresenter mPresenter;
    @BindView(R.id.upload_bt)
    Button upload_bt;
    @BindView(R.id.et_name)
    EditText et_name;

    @OnClick(R.id.iv_back)
    void Back() {
        finish();
    }

    @OnClick(R.id.upload_bt)
    void goHome() {
        //上传信息,如果上传成功给toast
        if (getIntent().getBooleanExtra("isMine", false)) {
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        ToastUtils.showLongToast("提交成功，我们尽快搜找片源");
    }

    @Override
    public void initInject() {
        App.getInstance().getActivityComponent().inject(this);
    }

    @Override
    public NeedMoviePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_needmovie;
    }

    @Override
    public void initViewAndEvent() {
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString().trim())) {
                    upload_bt.setClickable(true);
                    upload_bt.setBackgroundResource(R.drawable.bg_theme);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }
}
