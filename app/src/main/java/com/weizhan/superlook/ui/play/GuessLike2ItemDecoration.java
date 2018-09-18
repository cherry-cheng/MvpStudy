package com.weizhan.superlook.ui.play;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.widget.adapter.DefaultAdapterWrapper;
import com.weizhan.superlook.R;

/**
 * Created by Administrator on 2018/9/18.
 */

public class GuessLike2ItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 注意因为Adapter的封装，外部构造的spanSizeLookup在Adapter中的
     * {@link DefaultAdapterWrapper#onAttachedToRecyclerView(RecyclerView)}中被包裹一层，所以不能直接传入
     * 用做参数，用时应从RecyclerView对象中获取
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup();
        int spanSize = spanSizeLookup.getSpanSize(position);
        int margin_normal = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_normal);
        int margin_small = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_small);
        int margin_4 = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_4);
        int margin_10 = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_10);
        int margin_2 = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_2);
        int index = spanSizeLookup.getSpanIndex(position , 2);
        switch (index) {
            case 0:
                outRect.left = margin_small;
                outRect.right = margin_4;
                break;
            case 1:
                outRect.left = margin_4;
                outRect.right = margin_small;
                break;
        }
        outRect.bottom = margin_2;
        outRect.top = margin_small;
    }
}
