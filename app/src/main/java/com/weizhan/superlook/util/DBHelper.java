package com.weizhan.superlook.util;

import com.weizhan.superlook.model.bean.search.SearchKey;

import java.util.List;

import io.realm.Realm;


/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public interface DBHelper {
    Realm getRealm();
    void insertSearchHistory(SearchKey bean);

    List<SearchKey> getSearchHistoryList(String value);

    void deleteSearchHistoryList(String value);

    void deleteSearchHistoryAll();

    List<SearchKey> getSearchHistoryListAll();
}
