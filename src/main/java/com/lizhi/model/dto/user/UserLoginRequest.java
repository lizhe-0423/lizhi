package com.lizhi.model.dto.user;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>

 * @description 用户登录请求
 * @data 2023 2023/10/7 18:11
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;

}
