package com.weizhan.superlook.model.bean.search;

import io.realm.RealmObject;

/**
 * Created by zjg on 2016/10/11.
 */

public class SearchKey extends RealmObject {
    public String searchKey;
    public long insertTime;//插入时间

    public SearchKey() {
    }

    public SearchKey(String suggestion, long insertTime) {
        this.searchKey = suggestion;
        this.insertTime = insertTime;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }
}
