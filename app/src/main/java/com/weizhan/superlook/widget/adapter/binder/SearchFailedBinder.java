package com.weizhan.superlook.widget.adapter.binder;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.util.ToastUtils;
import com.common.widget.adapter.BaseLoadFailedBinder;
import com.common.widget.adapter.BaseViewHolder;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.event.JumpActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/11.
 */

public class SearchFailedBinder extends BaseLoadFailedBinder<SearchFailedBinder.LoadFailedHolder> {

    private static final int NO_ID = -1;

    private int resId = NO_ID;

    private int stringId = NO_ID;

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }

    public void setStringId(@StringRes int stringId) {
        this.stringId = stringId;
    }

    @NonNull
    @Override
    protected SearchFailedBinder.LoadFailedHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.search_load_failed, parent, false);
        return new SearchFailedBinder.LoadFailedHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull final SearchFailedBinder.LoadFailedHolder holder) {
        if (resId != NO_ID) {
            holder.ivLoadFailed.setImageResource(resId);
        }
        if (stringId != NO_ID) {
            holder.tvLoadFailed.setText(stringId);
        }
        holder.request_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showLongToast("点击了我要求片");
                EventBus.getDefault().post(new JumpActivity());
            }
        });
    }

    class LoadFailedHolder extends BaseViewHolder {

        @BindView(R.id.iv_load_failed)
        ImageView ivLoadFailed;
        @BindView(R.id.tv_load_failed)
        TextView tvLoadFailed;
        @BindView(R.id.request_tv)
        TextView request_tv;

        public LoadFailedHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
