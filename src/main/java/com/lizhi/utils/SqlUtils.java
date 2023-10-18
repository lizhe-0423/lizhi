package com.lizhi.utils;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description SQL 工具
 * @data 2023 2023/10/9 9:45
 */
public class SqlUtils {
    private SqlUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static boolean validSortField(String sortField) {
        if (CharSequenceUtil.isBlank(sortField)){
            return false;
        }
        return !CharSequenceUtil.containsAny(sortField,"=","(",")"," ");
    }
}
