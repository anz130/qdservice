package com.paopao.roblistapp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class IndentItemResult {
    private int code;
    private String message;
    private List<IndentItem> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<IndentItem> getData() {
        return data;
    }

    public void setData(List<IndentItem> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
