package com.weizhan.superlook.ui.variety.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.variety.AppVarietyShow;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/5.
 */

public class VarietyPartitionItemViewBinder extends ItemViewBinder<AppVarietyShow.Partition, VarietyPartitionItemViewBinder.PartitionViewHolder> {

    @NonNull
    @Override
    protected VarietyPartitionItemViewBinder.PartitionViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_variety_partition, parent, false);
        return new VarietyPartitionItemViewBinder.PartitionViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull VarietyPartitionItemViewBinder.PartitionViewHolder holder, @NonNull AppVarietyShow.Partition item) {
        holder.tvName.setText(item.getTitle());
    }

    static class PartitionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        public PartitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
