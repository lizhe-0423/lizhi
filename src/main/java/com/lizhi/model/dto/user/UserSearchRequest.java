package com.lizhi.model.dto.user;

import com.lizhi.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 查询用户请求
 * @data 2023 2023/10/9 9:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserSearchRequest extends PageRequest implements Serializable {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userPhoto;
    /**
     * 用户角色等级
     */
    private String userLevel;
}
