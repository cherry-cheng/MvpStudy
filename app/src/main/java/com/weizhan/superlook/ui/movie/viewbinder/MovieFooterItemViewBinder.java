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

public class MovieFooterItemViewBinder extends ItemViewBinder<MovieFooterItemViewBinder.MovieFooter, MovieFooterItemViewBinder.MovieFooterViewHolder> {

    @NonNull
    @Override
    protected MovieFooterItemViewBinder.MovieFooterViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_more, parent, false);
        return new MovieFooterItemViewBinder.MovieFooterViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull MovieFooterItemViewBinder.MovieFooterViewHolder holder, @NonNull MovieFooterItemViewBinder.MovieFooter item) {
        String region = holder.tvMore.getContext().getString(R.string.more_format, item.getMovie());
        holder.tvMore.setText(region);
    }

    static class MovieFooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_more)
        TextView tvMore;

        public MovieFooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class MovieFooter {

        private String region;

        public String getMovie() {
            return region;
        }

        public void setMovie(String region) {
            this.region = region;
        }
    }
}
