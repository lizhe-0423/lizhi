package com.lizhi.constant;

import lombok.Getter;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 通用常量
 * @data 2023 2023/10/9 9:13
 */
@Getter
public enum CommonConstant {
    /**
     * error
     */
    ERROR(),
    /**
     * 页面升序
     */
    PAGE_SORT_ORDER_ASC(),
    /**
     * 页面降序
     */
    PAGE_SORT_ORDER_DESC(),
    /**
     * 通用字段大小限制
     */
    FIELD_MAX;

    private final int fieldMax=8;

}