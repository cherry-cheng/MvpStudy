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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/11.
 */

public class SearchNormalFailedBinder extends BaseLoadFailedBinder<SearchNormalFailedBinder.LoadFailedHolder> {

    private static final int NO_ID = -1;

    private int resId = NO_ID;

    private int stringId = NO_ID;

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }

    public void setStringId(@StringRes int stringId) {
        this.stringId = stringId;
    }

    public SearchNormalFailedBinder() {

    }

    public SearchNormalFailedBinder(@DrawableRes int resId, @StringRes int stringId) {
        setResId(resId);
        setStringId(stringId);
    }

    @NonNull
    @Override
    protected SearchNormalFailedBinder.LoadFailedHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.search_normal_failed, parent, false);
        return new SearchNormalFailedBinder.LoadFailedHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchNormalFailedBinder.LoadFailedHolder holder) {
        if (resId != NO_ID) {
            holder.ivLoadFailed.setImageResource(resId);
        }
        if (stringId != NO_ID) {
            holder.tvLoadFailed.setText(stringId);
        }
    }

    class LoadFailedHolder extends BaseViewHolder {

        @BindView(R.id.iv_load_failed)
        ImageView ivLoadFailed;
        @BindView(R.id.tv_load_failed)
        TextView tvLoadFailed;

        public LoadFailedHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
