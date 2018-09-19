package com.weizhan.superlook.ui.play;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.play.TestBean;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/19.
 */

public class GuessTitleViewBinder extends ItemViewBinder<TestBean, GuessTitleViewBinder.GuessTitleViewHolder> {

    @NonNull
    @Override
    protected GuessTitleViewBinder.GuessTitleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.play_partition_item, parent, false);
        return new GuessTitleViewBinder.GuessTitleViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull final GuessTitleViewBinder.GuessTitleViewHolder holder, @NonNull TestBean item) {
    }

    static class GuessTitleViewHolder extends RecyclerView.ViewHolder {
        public GuessTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
