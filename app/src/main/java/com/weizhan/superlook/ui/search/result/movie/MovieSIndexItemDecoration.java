package com.weizhan.superlook.ui.search.result.movie;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.widget.adapter.DefaultAdapterWrapper;
import com.weizhan.superlook.R;

/**
 * Created by Administrator on 2018/9/5.
 */

public class MovieSIndexItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 注意因为Adapter的封装，外部构造的spanSizeLookup在Adapter中的
     * {@link DefaultAdapterWrapper#onAttachedToRecyclerView(RecyclerView)}中被包裹一层，所以不能直接传入
     * 用做参数，用时应从RecyclerView对象中获取
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int margin_small = view.getContext().getResources().getDimensionPixelOffset(R.dimen.margin_small);
        outRect.left = margin_small;
        outRect.right = margin_small;
        outRect.bottom = margin_small;
        outRect.top = margin_small;
    }
}
