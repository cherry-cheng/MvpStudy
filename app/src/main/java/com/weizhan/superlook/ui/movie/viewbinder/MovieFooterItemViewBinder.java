package com.weizhan.superlook.ui.movie.viewbinder;

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

public class MovieFooterItemViewBinder extends ItemViewBinder<MovieFooterItemViewBinder.Recommend1Footer, MovieFooterItemViewBinder.Recommend1FooterViewHolder> {

    @NonNull
    @Override
    protected MovieFooterItemViewBinder.Recommend1FooterViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_more, parent, false);
        return new MovieFooterItemViewBinder.Recommend1FooterViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull MovieFooterItemViewBinder.Recommend1FooterViewHolder holder, @NonNull MovieFooterItemViewBinder.Recommend1Footer item) {
        String region = holder.tvMore.getContext().getString(R.string.more_format, item.getRecommend1());
        holder.tvMore.setText(region);
    }

    static class Recommend1FooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_more)
        TextView tvMore;

        public Recommend1FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class Recommend1Footer {

        private String region;

        public String getRecommend1() {
            return region;
        }

        public void setRecommend1(String region) {
            this.region = region;
        }
    }
}
