package com.weizhan.superlook.ui.play;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/19.
 */

public class PlayTitleItemViewBinder extends ItemViewBinder<AppRecommend1Show.Partition, PlayTitleItemViewBinder.PlayTitleViewHolder> {

    @NonNull
    @Override
    protected PlayTitleItemViewBinder.PlayTitleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.play_title_item, parent, false);
        return new PlayTitleItemViewBinder.PlayTitleViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull final PlayTitleItemViewBinder.PlayTitleViewHolder holder, @NonNull AppRecommend1Show.Partition item) {
        final Context context = holder.iv_displayIntro.getContext();
        holder.iv_displayIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.iv_displayIntro.isSelected()) {
                    holder.iv_displayIntro.setSelected(false);
                    holder.ll_display.setVisibility(View.GONE);
                } else {
                    holder.iv_displayIntro.setSelected(true);
                    holder.ll_display.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    static class PlayTitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_displayIntro)
        ImageView iv_displayIntro;
        @BindView(R.id.ll_display)
        LinearLayout ll_display;
        @BindView(R.id.title)
        TextView title;

        public PlayTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
