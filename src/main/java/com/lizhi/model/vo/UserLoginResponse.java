package com.lizhi.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>

 * @description
 * @data 2023 2023/10/7 18:24
 */
@Data
public class UserLoginResponse implements Serializable {
    /**
     * 用户 id
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userPhoto;
    /**
     * 用户等级：Normal/VIP/Admin
     */
    private String userLever;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * token
     */
    private String token;

    private static final long serialVersionUID = 1L;
}
