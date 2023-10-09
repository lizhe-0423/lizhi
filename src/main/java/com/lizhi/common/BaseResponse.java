package com.lizhi.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>

 * @description 通用返回类
 * @data 2023 2023/10/7 18:22
 */
@Data
public class BaseResponse<T> implements Serializable {
    private int code;
    private transient T data;
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code=code;
        this.data=data;
        this.message=message;
    }
}
