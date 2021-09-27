package com.linchtech.myapplication.utils;

/**
 * HTTP请求类型
 * user：lixiaoda
 * date：2021/1/29
 */
public enum RequestMethod {

    GET("GET"),

    POST("POST"),

    HEAD("HEAD"),

    OPTIONS("OPTIONS"),

    PUT("PUT"),

    DELETE("DELETE"),

    TRACE("TRACE")
    ;

    private String method;

    RequestMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
