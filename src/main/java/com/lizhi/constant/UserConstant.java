package com.lizhi.constant;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 用户常量类
 * @data 2023 2023/10/8 9:36
 */

public enum UserConstant {
    /**
     * 默认用户
     */
    USER(),
    /**
     * VIP用户
     */
     VIP(),
    /**
     * 管理员用户
     */
    ADMIN(),
    /**
     * 禁用用户
     */
    BAN();

    UserConstant(){
    }
}
