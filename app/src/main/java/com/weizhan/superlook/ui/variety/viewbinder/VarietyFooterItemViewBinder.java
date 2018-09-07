package com.weizhan.superlook.ui.variety.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weizhan.superlook.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/5.
 */

public class VarietyFooterItemViewBinder extends ItemViewBinder<VarietyFooterItemViewBinder.VarietyFooter, VarietyFooterItemViewBinder.VarietyFooterViewHolder> {

    @NonNull
    @Override
    protected VarietyFooterItemViewBinder.VarietyFooterViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_more, parent, false);
        return new VarietyFooterItemViewBinder.VarietyFooterViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull VarietyFooterItemViewBinder.VarietyFooterViewHolder holder, @NonNull VarietyFooterItemViewBinder.VarietyFooter item) {
        String region = holder.tvMore.getContext().getString(R.string.more_format, item.getVariety());
        holder.tvMore.setText(region);
    }

    static class VarietyFooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_more)
        TextView tvMore;

        public VarietyFooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class VarietyFooter {

        private String region;

        public String getVariety() {
            return region;
        }

        public void setVariety(String region) {
            this.region = region;
        }
    }
}
