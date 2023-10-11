package com.lizhi.utils;

import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 异常简单处理工具类
 * @data 2023 2023/10/11 17:20
 */
public class ThrowUtil {
    /**
     * 如果condition 为true 就抛出异常
     * @param condition 条件
     * @param exception 异常
     */
    public static void throwIf(boolean condition ,RuntimeException exception){
        if(condition){
            throw exception;
        }
    }

    /**
     * 如果condition 为true 就抛出异常
     * @param condition 条件
     * @param errorCode 错误代码
     */
    public static void throwIf(boolean condition , ErrorCode errorCode){
            throwIf(condition,new BusinessException(errorCode));
    }

    /**
     * 如果condition 为 true 就抛出异常
     * @param condition 条件
     * @param errorCode 错误代码
     * @param message 错误内容
     */
    public static void throwIf(boolean condition ,ErrorCode errorCode,String message){
             throwIf(condition,new BusinessException(errorCode,message));
    }

}
