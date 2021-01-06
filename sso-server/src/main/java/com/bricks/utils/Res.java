package com.bricks.utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class Res<T> implements Serializable {
    private int status;
    private String desc;
    private T data;

    public static Res build() {
        return build(0);
    }

    public static Res build(int status) {
        return build(status, null);
    }

    public static Res build(String desc) {
        return build(0, desc);
    }

    public static Res build(int status, String desc) {
        return build(status, desc, null);
    }

    public static <T> Res build(T data,int status) {
        return build(status, null, data);
    }

    public static <T> Res build(int status, String desc, T data) {
        return new Res(status, desc, data);
    }

    private Res(int status, String desc, T data) {
        this.status = status;
        this.desc = desc;
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getStatus() {
        return this.status;
    }

    public String getDesc() {
        return this.desc;
    }

    public T getData() {
        return this.data;
    }
}
