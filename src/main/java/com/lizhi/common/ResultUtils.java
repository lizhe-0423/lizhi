package com.lizhi.common;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>

 * @description 返回工具类
 * @data 2023 2023/10/8 9:21
 *
 */
public class ResultUtils {
    private ResultUtils() {
        throw new IllegalStateException("Utility class");
    }
     public static <T> BaseResponse<T> success(T data){
         return new BaseResponse<>(0,data,"ok");
     }
    public static <T> BaseResponse<T> error(int code, String message){
        return new BaseResponse<>(code, null,message);
    }
    public static <T> BaseResponse<T> error(ErrorCode errorCode,String message){
        return new BaseResponse<>(errorCode.getCode(),null,message);
    }
}
