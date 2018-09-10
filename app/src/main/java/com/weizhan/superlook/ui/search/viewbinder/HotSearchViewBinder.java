package com.weizhan.superlook.ui.search.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.search.HotWord;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/10.
 */

public class HotSearchViewBinder extends ItemViewBinder<HotWord, HotSearchViewBinder.HotSearchViewHolder> {

    @NonNull
    @Override
    protected HotSearchViewBinder.HotSearchViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_hotsearch, parent, false);
        return new HotSearchViewBinder.HotSearchViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull HotSearchViewBinder.HotSearchViewHolder holder, @NonNull HotWord item) {
        if (holder.getPosition() == 0) {
            holder.tv_number.setBackgroundResource(R.drawable.bg_hotsearch);
        } else if (holder.getPosition() == 1) {
            holder.tv_number.setBackgroundResource(R.drawable.bg_hotsearch2);
        } else if (holder.getPosition() == 2) {
            holder.tv_number.setBackgroundResource(R.drawable.bg_hotsearch3);
        } else {
            holder.tv_number.setBackgroundResource(R.drawable.bg_hotsearchword);
        }
        holder.tv_number.setText((holder.getPosition() + 1) + "");
        holder.hotTv.setText(item.getKeyword());
    }

    static class HotSearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_number)
        TextView tv_number;
        @BindView(R.id.hotTv)
        TextView hotTv;

        public HotSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
