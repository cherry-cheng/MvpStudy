package com.weizhan.superlook.widget.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weizhan.superlook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿照原生RecyclerView.Adapter的实现，在原生适配器的基础上 支持监听item单击事件以及支持单选模式、多选模式
 * Created by Administrator on 2018/1/4.
 */

public class EasyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private OnItemClickListener onItemClickListener;
    private OnItemSingleSelectListener onItemSingleSelectListener;

    private List<String> list = new ArrayList<String>();
    private Context mContext;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<String> newList) {
        if (newList != null && newList.size() > 0)
        this.list.clear();
        this.list = newList;
        notifyDataSetChanged();
    }

    public EasyAdapter(List<String> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    public void setOnItemSingleSelectListener(OnItemSingleSelectListener onItemSingleSelectListener) {
        this.onItemSingleSelectListener = onItemSingleSelectListener;
    }

    private int singleSelected = 0; // 默认为第一个被选中

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_text, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder)holder).textView.setTag(position);//绑定
        ((MyViewHolder)holder).textView.setText(list.get(position));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        if (singleSelected == position) {
            holder.itemView.setSelected(true);
            ((MyViewHolder)holder).textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            ((MyViewHolder)holder).textView.setTextColor(Color.parseColor("#ff850b"));
        } else {
            holder.itemView.setSelected(false);
            ((MyViewHolder)holder).textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            ((MyViewHolder)holder).textView.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        int itemPosition = (int) v.getTag();
        if (onItemSingleSelectListener != null) {
            if (singleSelected == itemPosition) {
                onItemSingleSelectListener.onSelected(itemPosition, false);
            } else {
                singleSelected = itemPosition;
                onItemSingleSelectListener.onSelected(itemPosition, true);
            }
        }
            notifyDataSetChanged();//通知刷新
    }

    //=========API=========

    /**
     * 设置默认选中项
     *
     * @param itemPositions
     */

    public void setSelected(int... itemPositions) {
        singleSelected = itemPositions[0];
        if (onItemSingleSelectListener != null) {
            onItemSingleSelectListener.onSelected(singleSelected, true);
        }
        notifyDataSetChanged();
    }


    /**
     * 获取单选模式选中Item位置
     *
     * @return
     */
    public int getSingleSelected() {
        return singleSelected;
    }

    /**
     * 获取单选项位置
     */
    public int getSingleSelectedPosition() {
        return singleSelected;
    }

    /**
     * 判断某个item位置是否被选中
     *
     * @param position
     * @return
     */
    public boolean isSelected(int position) {
        return position == singleSelected;
    }

    /**
     * 点选模式监听接口
     */
    public interface OnItemClickListener {
        /**
         * 点选模式下，点击item时回调
         *
         * @param itemPosition 点击的item位置
         */
        void onClicked(int itemPosition);
    }

    /**
     * 单选模式监听接口
     */
    public interface OnItemSingleSelectListener {
        /**
         * 单选模式下，点击Item选中时回调
         *
         * @param itemPosition 点击的item位置
         * @param isSelected   是否选中
         */
        void onSelected(int itemPosition, boolean isSelected);

    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_item);
        }
    }
}
