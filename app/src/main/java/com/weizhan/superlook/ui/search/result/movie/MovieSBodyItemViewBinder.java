package com.weizhan.superlook.ui.search.result.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.recommend1.AppRecommend1Show;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/5.
 */

public class MovieSBodyItemViewBinder extends ItemViewBinder<AppRecommend1Show.Body, MovieSBodyItemViewBinder.Recommend1BodyViewHolder> {

    @NonNull
    @Override
    protected MovieSBodyItemViewBinder.Recommend1BodyViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_allsearch_body, parent, false);
        return new MovieSBodyItemViewBinder.Recommend1BodyViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull MovieSBodyItemViewBinder.Recommend1BodyViewHolder holder, @NonNull AppRecommend1Show.Body item) {
        Context context = holder.ivCover.getContext();
        holder.ivCover.setImageURI(item.getCover());
        holder.big_tv.setText(item.getTitle());
        holder.update_tv.setVisibility(View.GONE);
    }

    static class Recommend1BodyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        SimpleDraweeView ivCover;
        @BindView(R.id.score)
        TextView score;
        @BindView(R.id.big_tv)
        TextView big_tv;
        @BindView(R.id.small_des)
        TextView small_des;
        @BindView(R.id.category_tv)
        TextView category_tv;
        @BindView(R.id.update_tv)
        TextView update_tv;

        public Recommend1BodyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
