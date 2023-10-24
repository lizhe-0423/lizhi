package com.lizhi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lizhi.model.dto.user.UserByIdRequest;
import com.lizhi.model.dto.user.UserSearchRequest;
import com.lizhi.model.entity.Users;


/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【users】的数据库操作Service
* @createDate 2023-10-07 17:45:20
*/
public interface UsersService extends IService<Users> {
    /**
     * 用户注册事务
     * @param userAccount 账号
     * @param userPassword 密码
     * @return 用户id
     */
     Long userRegister(String userAccount,String userPassword);


    /**
     * 向数据库填充默认注册用户数据
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 注册的用户
     */
    Users createUser(String userAccount, String userPassword);

    /**
     * 登录当前用户
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 当前登录用户id
     */
    Users getLoginUser(String userAccount, String userPassword);

    /**
     * 获取查询条件
     * @param userSearchRequest 用户查询请求
     * @return 实体对象封装操作类
     */
    QueryWrapper<Users> getQueryWrapper(UserSearchRequest userSearchRequest);

    /**
     * 对需要进行查询、删除的用户id请求数据进行判断
     * @param userByIdRequest 用户id请求
     * @return 用户id
     */
    Long isRoleGetUserById(UserByIdRequest userByIdRequest);

    /**
     * 检查当前用户是否登录 并返回当前用户id
     * @return 用户id
     */
    Long getUserByCurrent();
}
