package com.weizhan.superlook.model.bean.search;

public class HotWordBean {

    public static final int NORMAL = 0;
    public static final int RED = 1;

    private String word;
    private int type;

    public HotWordBean(String word, int type) {
        this.word = word;
        this.type = type;
    }

    public HotWordBean(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
