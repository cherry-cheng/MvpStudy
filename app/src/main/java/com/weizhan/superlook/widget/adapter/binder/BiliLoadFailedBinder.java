package com.weizhan.superlook.widget.adapter.binder;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.widget.adapter.BaseLoadFailedBinder;
import com.common.widget.adapter.BaseViewHolder;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.RefreshEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by miserydx on 17/12/20.
 */

public class BiliLoadFailedBinder extends BaseLoadFailedBinder<BiliLoadFailedBinder.LoadFailedHolder> {

    private static final int NO_ID = -1;

    private int resId = NO_ID;

    private int stringId = NO_ID;

    private int refreshType = 99;

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }

    public void setStringId(@StringRes int stringId) {
        this.stringId = stringId;
    }

    public void setRefreshType(int refreshType) {
        this.refreshType = refreshType;
    }

    public BiliLoadFailedBinder() {

    }

    public BiliLoadFailedBinder(int refreshType) {
        this.refreshType = refreshType;
    }

    @NonNull
    @Override
    protected LoadFailedHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_load_failed, parent, false);
        return new LoadFailedHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull final LoadFailedHolder holder) {
        if (resId != NO_ID) {
            holder.ivLoadFailed.setImageResource(resId);
        }
        if (stringId != NO_ID) {
            holder.tvLoadFailed.setText(stringId);
        }

        holder.refresh_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefreshEvent refreshEvent = new RefreshEvent();
                refreshEvent.setTab(refreshType);
                EventBus.getDefault().post(refreshEvent);
            }
        });
    }

    class LoadFailedHolder extends BaseViewHolder {

        @BindView(R.id.iv_load_failed)
        ImageView ivLoadFailed;
        @BindView(R.id.tv_load_failed)
        TextView tvLoadFailed;
        @BindView(R.id.refresh_tv)
        TextView refresh_tv;

        public LoadFailedHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
