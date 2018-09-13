package com.weizhan.superlook.ui.mine;
import com.common.base.BaseMvpFragment;
import com.weizhan.superlook.App;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.RefreshEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import me.drakeet.multitype.Items;

public class MineFragment extends BaseMvpFragment<MinePresenter> implements MineContract.View {

    public static final String TAG = MineFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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
