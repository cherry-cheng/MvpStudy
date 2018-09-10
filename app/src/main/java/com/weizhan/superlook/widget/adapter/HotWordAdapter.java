package com.weizhan.superlook.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weizhan.superlook.R;
import com.weizhan.superlook.model.bean.search.HotWord;
import com.weizhan.superlook.model.bean.search.SearchKey;

import java.util.List;

import static com.weizhan.superlook.model.bean.search.HotWordBean.NORMAL;
import static com.weizhan.superlook.model.bean.search.HotWordBean.RED;

/**
 * Created by Administrator on 2018/9/10.
 */

public class HotWordAdapter extends BaseAdapter {

    private Context mContext;
    private List<SearchKey> mList;

    public HotWordAdapter(Context context, List<SearchKey> list) {
        mContext = context;
        mList = list;
    }


    public void setData(List<SearchKey> lists) {
        if (lists == null) {
            return;
        }
        mList.clear();
        mList.addAll(lists);
        this.notifyDataSetChanged();
    }

    public void removeAllData() {
        mList.clear();
        removeAllData();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position) == null ? 0 : 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView;
        switch (getItemViewType(position)) {
            case RED:
                textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_hot_word_red, null);
                break;
            case NORMAL:
            default:
                textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_hot_word, null);
                break;
        }
        SearchKey bean = mList.get(position);
        textView.setText(bean.getSearchKey());
        return textView;
    }
}
