package com.lizhi.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizhi.common.BaseResponse;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.common.ResultUtils;
import com.lizhi.model.dto.user.UserLoginRequest;
import com.lizhi.model.dto.user.UserRegisterRequest;
import com.lizhi.model.dto.user.UserSearchRequest;
import com.lizhi.model.entity.Users;
import com.lizhi.model.vo.UserLoginResponse;
import com.lizhi.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 用户接口
 * @data 2023 2023/10/7 18:05
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UsersService usersService;

    /**
     * 用户登录接口
     * @param userLoginRequest 用户登录传参
     * @return BaseResponse<UserLoginResponse>
     */
    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest){
        if(userLoginRequest==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Users loginUserInfo = usersService.getLoginUser(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword());
        StpUtil.login(loginUserInfo);
        UserLoginResponse loginResponse = new UserLoginResponse();
        BeanUtil.copyProperties(loginUserInfo,loginResponse);
        loginResponse.setToken(StpUtil.getTokenValue());
        return   ResultUtils.success(loginResponse);
    }

    /**
     * 用户注册接口
     * @param userRegisterRequest 用户注册请求
     * @return BaseResponse<Long>
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Long userRegisterId = usersService.userRegister(userRegisterRequest.getUserAccount(), userRegisterRequest.getUserPassword());
        return   ResultUtils.success(userRegisterId);
    }

    /**
     * 分页获取用户信息
     * @param userSearchRequest 用户查询请求
     * @return BaseResponse<Page<Users>>
     */
    @PostMapping("/page")
    public BaseResponse<Page<Users>> searchUser(@RequestBody UserSearchRequest userSearchRequest){
        long current = userSearchRequest.getCurrent();
        long pageSize = userSearchRequest.getPageSize();
        Page<Users> page = usersService.page(new Page<>(current, pageSize),
                usersService.getQueryWrapper(userSearchRequest));
        return ResultUtils.success(page);
    }


}
