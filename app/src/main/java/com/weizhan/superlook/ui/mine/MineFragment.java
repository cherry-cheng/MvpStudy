package com.weizhan.superlook.ui.mine;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.base.BaseMvpFragment;
import com.common.util.ToastUtils;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.RefreshEvent;
import com.weizhan.superlook.ui.search.need.NeedMovieActivity;
import com.weizhan.superlook.util.FileUtils;
import com.weizhan.superlook.widget.dialog.UpdateDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.Items;

public class MineFragment extends BaseMvpFragment<MinePresenter> implements MineContract.View {

    public static final String TAG = MineFragment.class.getSimpleName();

    @BindView(R.id.weixin_iv)
    ImageView weixin_iv;
    @BindView(R.id.weibo_iv)
    ImageView weibo_iv;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rl_login)
    RelativeLayout rl_login;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    @BindView(R.id.tv_loggout)
    TextView tv_loggout;
    @BindView(R.id.view6)
    View view6;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.weixin_iv)
    void weixinLogin() {
        //微信登录
    }

    @OnClick(R.id.weibo_iv)
    void weiboLogin() {
        //微博登录
    }

    @OnClick(R.id.tv_need)
    void needMovie() {
        //我要求片
        Intent intent = new Intent(getActivity(), NeedMovieActivity.class);
        intent.putExtra("isMine", true);
        startActivity(intent);
    }

    @OnClick(R.id.tv_share)
    void shareApp() {
        //分享应用
    }

    @OnClick(R.id.tv_clean)
    void cleanCache() {
        //清除缓存
        FileUtils.clearCache();
        ToastUtils.showLongToast("缓存清除成功");
    }

    @OnClick(R.id.tv_declare)
    void declareProtocle() {
        //免责声明
        startActivity(new Intent(getActivity(), DeclareActivity.class));
    }

    @OnClick(R.id.tv_update)
    void updateCheck() {
        //检查更新
        final UpdateDialog updateDialog = new UpdateDialog(getActivity(), "发现新版本",
                "现在更新", getResources().getString(R.string.update_content), true);
        updateDialog.show();
        updateDialog.setClickListener(new UpdateDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                updateDialog.dismiss();
                ToastUtils.showLongToast("正在下载");
            }

            @Override
            public void doCancel() {
                updateDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.tv_loggout)
    void logOut() {
        //退出登录
        rl_login.setVisibility(View.GONE);
        rl_title.setVisibility(View.VISIBLE);
        tv_loggout.setVisibility(View.GONE);
        view6.setVisibility(View.GONE);
        ToastUtils.showLongToast("已退出");
    }


    @Override
    protected void initInject() {
        App.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {

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
    public void onDataUpdated(Items items) {

    }

    @Override
    public void showLoadFailed() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshEvent refreshEvent) {

    }

}
