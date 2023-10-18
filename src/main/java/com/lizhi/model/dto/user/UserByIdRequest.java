package com.lizhi.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 删除用户请求
 * @data 2023 2023/10/9 16:27
 */
@Data
public class UserByIdRequest implements Serializable {
    private Long userId;
}
