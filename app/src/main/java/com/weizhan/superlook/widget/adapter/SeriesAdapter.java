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
 * Created by Administrator on 2018/9/18.
 */

public class SeriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private SeriesAdapter.OnItemClickListener onItemClickListener;
    private SeriesAdapter.OnItemSingleSelectListener onItemSingleSelectListener;

    private List<String> list = new ArrayList<String>();
    private Context mContext;
    public void setOnItemClickListener(SeriesAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<String> newList) {
        if (newList != null && newList.size() > 0)
            this.list.clear();
        this.list = newList;
        notifyDataSetChanged();
    }

    public SeriesAdapter(List<String> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    public void setOnItemSingleSelectListener(SeriesAdapter.OnItemSingleSelectListener onItemSingleSelectListener) {
        this.onItemSingleSelectListener = onItemSingleSelectListener;
    }

    private int singleSelected = 0; // 默认为第一个被选中

    @Override
    public SeriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_series_number, parent, false);
        return new SeriesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((SeriesAdapter.MyViewHolder)holder).textView.setTag(position);//绑定
        ((SeriesAdapter.MyViewHolder)holder).textView.setText(list.get(position));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        if (singleSelected == position) {
            holder.itemView.setSelected(true);
            ((SeriesAdapter.MyViewHolder)holder).textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            ((SeriesAdapter.MyViewHolder)holder).textView.setTextColor(Color.parseColor("#ffffff"));
            ((SeriesAdapter.MyViewHolder)holder).textView.setBackgroundResource(R.drawable.bg_hotsearch);
        } else {
            holder.itemView.setSelected(false);
            ((SeriesAdapter.MyViewHolder)holder).textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            ((SeriesAdapter.MyViewHolder)holder).textView.setTextColor(Color.parseColor("#000000"));
            ((SeriesAdapter.MyViewHolder)holder).textView.setBackgroundResource(R.drawable.bg_white_corner);
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
