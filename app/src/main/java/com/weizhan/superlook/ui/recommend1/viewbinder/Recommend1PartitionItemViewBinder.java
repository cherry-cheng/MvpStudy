package com.weizhan.superlook.ui.recommend1.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/5.
 */

public class Recommend1PartitionItemViewBinder extends ItemViewBinder<AppRecommend1Show.Partition, Recommend1PartitionItemViewBinder.PartitionViewHolder> {

    @NonNull
    @Override
    protected Recommend1PartitionItemViewBinder.PartitionViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_recommend1_partition, parent, false);
        return new Recommend1PartitionItemViewBinder.PartitionViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull Recommend1PartitionItemViewBinder.PartitionViewHolder holder, @NonNull AppRecommend1Show.Partition item) {
        holder.ivIcon.setBackgroundResource(item.getLogo());
        holder.tvName.setText(item.getTitle());
    }

    static class PartitionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;

        public PartitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
