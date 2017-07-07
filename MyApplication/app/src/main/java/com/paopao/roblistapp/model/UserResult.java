package com.paopao.roblistapp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */

public class UserResult {
    private int code;
    private String message;
    private User data;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public User getData() {
        return data;
    }
}
