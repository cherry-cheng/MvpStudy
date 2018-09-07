package com.weizhan.superlook.ui.series.viewbinder;

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

public class SeriesFooterItemViewBinder extends ItemViewBinder<SeriesFooterItemViewBinder.SeriesFooter, SeriesFooterItemViewBinder.SeriesFooterViewHolder> {

    @NonNull
    @Override
    protected SeriesFooterItemViewBinder.SeriesFooterViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_more, parent, false);
        return new SeriesFooterItemViewBinder.SeriesFooterViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull SeriesFooterItemViewBinder.SeriesFooterViewHolder holder, @NonNull SeriesFooterItemViewBinder.SeriesFooter item) {
        String region = holder.tvMore.getContext().getString(R.string.more_format, item.getSeries());
        holder.tvMore.setText(region);
    }

    static class SeriesFooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_more)
        TextView tvMore;

        public SeriesFooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class SeriesFooter {

        private String region;

        public String getSeries() {
            return region;
        }

        public void setSeries(String region) {
            this.region = region;
        }
    }
}
