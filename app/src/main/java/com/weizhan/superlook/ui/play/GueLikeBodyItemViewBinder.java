package com.weizhan.superlook.ui.play;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.util.StringUtil;
import com.common.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/18.
 */

public class GueLikeBodyItemViewBinder extends ItemViewBinder<AppRecommend1Show.Body, GueLikeBodyItemViewBinder.GueLikeBodyViewHolder> {

    @NonNull
    @Override
    protected GueLikeBodyItemViewBinder.GueLikeBodyViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_guelike_body, parent, false);
        return new GueLikeBodyItemViewBinder.GueLikeBodyViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull GueLikeBodyItemViewBinder.GueLikeBodyViewHolder holder, @NonNull AppRecommend1Show.Body item) {
        Context context = holder.ivCover.getContext();
/*        if (holder.getPosition() == 5) {
            int width = ScreenUtil.getScreenWidth(context) / 3 - SystemUtil.dp2px(context, 8);
            int height = context.getResources().getDimensionPixelSize(R.dimen.recommend1_cover_height1);
            ImageUtil.load(holder.ivCover, item.getCover(), width, height);
        } else {
            int width = ScreenUtil.getScreenWidth(context) / 2 - SystemUtil.dp2px(context, 8);
            int height = context.getResources().getDimensionPixelSize(R.dimen.recommend1_cover_height);
            ImageUtil.load(holder.ivCover, item.getCover(), width, height);
        }*/
        holder.ivCover.setImageURI(item.getCover());
        holder.tvAreaTitle.setText(item.getTitle());
//        holder.tvPlay.setText(StringUtil.numberToWord(item.getPlay()));
        String favourite = StringUtil.numberToWord(item.getFavourite());
/*        if (!TextUtils.equals("0", favourite)) {
            holder.tvDanmaku.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_promo_index_follow, 0, 0, 0);
            holder.tvDanmaku.setText(favourite);
        } else {
            holder.tvDanmaku.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bangumi_common_ic_video_danmakus, 0, 0, 0);
            holder.tvDanmaku.setText(StringUtil.numberToWord(item.getDanmaku()));
        }
        holder.tvDanmaku.setCompoundDrawablePadding(SystemUtil.dp2px(holder.tvDanmaku.getContext(), 2));
        holder.tvDanmaku.setCompoundDrawableTintList(ColorStateList.valueOf(0xffa5a5a5));*/
        holder.item_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showLongToast("点击了影片");
            }
        });
    }

    static class GueLikeBodyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        SimpleDraweeView ivCover;
        @BindView(R.id.tv_area_title)
        TextView tvAreaTitle;
        @BindView(R.id.item_rl)
        RelativeLayout item_rl;

        public GueLikeBodyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
