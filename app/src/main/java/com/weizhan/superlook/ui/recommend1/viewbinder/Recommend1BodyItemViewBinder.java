package com.weizhan.superlook.ui.recommend1.viewbinder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.util.ImageUtil;
import com.common.util.ScreenUtil;
import com.common.util.StringUtil;
import com.common.util.SystemUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/5.
 */

public class Recommend1BodyItemViewBinder extends ItemViewBinder<AppRecommend1Show.Body, Recommend1BodyItemViewBinder.Recommend1BodyViewHolder> {

    @NonNull
    @Override
    protected Recommend1BodyItemViewBinder.Recommend1BodyViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_recommend1_body, parent, false);
        return new Recommend1BodyItemViewBinder.Recommend1BodyViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull Recommend1BodyItemViewBinder.Recommend1BodyViewHolder holder, @NonNull AppRecommend1Show.Body item) {
        Context context = holder.ivCover.getContext();
        int width = ScreenUtil.getScreenWidth(context) / 2 - SystemUtil.dp2px(context, 24);
        int height = context.getResources().getDimensionPixelSize(R.dimen.recommend_cover_height);
        ImageUtil.load(holder.ivCover, item.getCover(), width, height);
        holder.tvAreaTitle.setText(item.getTitle());
        holder.tvPlay.setText(StringUtil.numberToWord(item.getPlay()));
        String favourite = StringUtil.numberToWord(item.getFavourite());
        if (!TextUtils.equals("0", favourite)) {
            holder.tvDanmaku.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_promo_index_follow, 0, 0, 0);
            holder.tvDanmaku.setText(favourite);
        } else {
            holder.tvDanmaku.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bangumi_common_ic_video_danmakus, 0, 0, 0);
            holder.tvDanmaku.setText(StringUtil.numberToWord(item.getDanmaku()));
        }
        holder.tvDanmaku.setCompoundDrawablePadding(SystemUtil.dp2px(holder.tvDanmaku.getContext(), 2));
        holder.tvDanmaku.setCompoundDrawableTintList(ColorStateList.valueOf(0xffa5a5a5));
    }

    static class Recommend1BodyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        SimpleDraweeView ivCover;
        @BindView(R.id.tv_area_title)
        TextView tvAreaTitle;
        @BindView(R.id.tv_play)
        TextView tvPlay;
        @BindView(R.id.tv_danmaku)
        TextView tvDanmaku;

        public Recommend1BodyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
