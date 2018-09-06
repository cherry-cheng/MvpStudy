package com.weizhan.superlook.ui.recommend1;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.widget.adapter.DefaultAdapterWrapper;
import com.weizhan.superlook.R;

/**
 * Created by Administrator on 2018/9/5.
 */

public class Recommend1IndexItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 注意因为Adapter的封装，外部构造的spanSizeLookup在Adapter中的
     * {@link DefaultAdapterWrapper#onAttachedToRecyclerView(RecyclerView)}中被包裹一层，所以不能直接传入
     * 用做参数，用时应从RecyclerView对象中获取
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int position = parent.getChildLayoutPosition(view);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup();
        int spanSize = spanSizeLookup.getSpanSize(position);
        int margin_normal = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_normal);
        int margin_small = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_small);
        int margin_4 = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_4);
        int margin_10 = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_10);
        int margin_2 = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_2);
        if (spanSize == 6) {
            if (position == 0) { //header
                outRect.bottom = margin_small;
            } else {
                outRect.bottom = margin_10;
                outRect.top = margin_10;
            }
        } else if (spanSize == 3){
            outRect.bottom = margin_4;
            outRect.top = margin_4;
        } else {
            outRect.bottom = margin_4;
            outRect.top = margin_4;
        }

        if (itemPosition == 2 || itemPosition == 4) {
            outRect.left = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_small);
            outRect.right = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_4);
        }
        if (itemPosition == 3 || itemPosition == 5) {
            outRect.left = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_4);
            outRect.right = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_small);
        }
        if (itemPosition == 8 || itemPosition == 11) {
            outRect.left = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_small);
            outRect.right = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_2);
        }
        if (itemPosition == 9 || itemPosition == 12) {
            outRect.left = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_2);
            outRect.right = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_2);
        }
        if (itemPosition == 10 || itemPosition == 13) {
            outRect.left = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_2);
            outRect.right = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_small);
        }
    }
}
