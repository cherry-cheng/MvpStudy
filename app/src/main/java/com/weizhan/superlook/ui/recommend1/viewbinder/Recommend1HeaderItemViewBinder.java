package com.weizhan.superlook.ui.recommend1.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weizhan.superlook.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/5.
 */

public class Recommend1HeaderItemViewBinder extends ItemViewBinder<Recommend1HeaderItemViewBinder.Recommend1Header, Recommend1HeaderItemViewBinder.Recommend1HeaderViewHolder> {

    @NonNull
    @Override
    protected Recommend1HeaderItemViewBinder.Recommend1HeaderViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_recommend1_header, parent, false);
        return new Recommend1HeaderItemViewBinder.Recommend1HeaderViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull Recommend1HeaderItemViewBinder.Recommend1HeaderViewHolder holder, @NonNull Recommend1HeaderItemViewBinder.Recommend1Header item) {

    }

    static class Recommend1HeaderViewHolder extends RecyclerView.ViewHolder {

        public Recommend1HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class Recommend1Header {

    }
}
