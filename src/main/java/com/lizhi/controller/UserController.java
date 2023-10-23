package com.lizhi.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizhi.common.BaseResponse;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.common.ResultUtils;
import com.lizhi.constant.UserConstant;
import com.lizhi.model.dto.user.UserByIdRequest;
import com.lizhi.model.dto.user.UserLoginRequest;
import com.lizhi.model.dto.user.UserRegisterRequest;
import com.lizhi.model.dto.user.UserSearchRequest;
import com.lizhi.model.vo.UserLoginResponse;
import com.lizhi.service.UsersService;
import com.lizhicommen.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
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
     *
     * @param userLoginRequest 用户登录传参
     * @return BaseResponse<UserLoginResponse>
     */
    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Users loginUserInfo = usersService.getLoginUser(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword());
        StpUtil.login(loginUserInfo);
        StpUtil.getSession().set("user", loginUserInfo);
        UserLoginResponse loginResponse = new UserLoginResponse();
        BeanUtil.copyProperties(loginUserInfo, loginResponse);
        loginResponse.setToken(StpUtil.getTokenValue());
        return ResultUtils.success(loginResponse);
    }

    /**
     * 用户注册接口
     *
     * @param userRegisterRequest 用户注册请求
     * @return BaseResponse<Long>
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Long userRegisterId = usersService.userRegister(userRegisterRequest.getUserAccount(), userRegisterRequest.getUserPassword());
        return ResultUtils.success(userRegisterId);
    }

    @GetMapping("/logout")
    public BaseResponse<String> logoutUser() {
        if (StpUtil.isLogin()) {
            StpUtil.logout();
            return ResultUtils.success("已成功注销用户");
        }
        return ResultUtils.success("当前用户注销失败");
    }

    /**
     * 分页获取用户信息
     *
     * @param userSearchRequest 用户查询请求
     * @return BaseResponse<Page < Users>>
     */
    @PostMapping("/page")
    public BaseResponse<Page<Users>> searchUser(@RequestBody UserSearchRequest userSearchRequest) {
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "当前帐号未登录");
        }
        if (userSearchRequest == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请求数据为空");
        }
        long current = userSearchRequest.getCurrent();
        long pageSize = userSearchRequest.getPageSize();
        Page<Users> page = usersService.page(new Page<>(current, pageSize),
                usersService.getQueryWrapper(userSearchRequest));
        return ResultUtils.success(page);
    }

    /**
     * 获取单个用户信息接口
     *
     * @param userByIdRequest 用户id
     * @return 用户信息
     */
    @PostMapping("/getUserById")
    public BaseResponse<Users> getUserById(@RequestBody UserByIdRequest userByIdRequest) {
        if (!StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "当前帐号未登录");
        }
        Long userId = usersService.isRoleGetUserById(userByIdRequest);
        Users userById = usersService.getById(userId);
        return ResultUtils.success(userById);
    }


    /**
     * 删除用户接口
     *
     * @param userByIdRequest 用户id
     * @return 成功or失败 message
     */
    @DeleteMapping("/delUserById")
    public BaseResponse<String> delUserById(@RequestBody UserByIdRequest userByIdRequest) {
        Long userId = usersService.isRoleGetUserById(userByIdRequest);
        if (!StpUtil.hasRole(UserConstant.ADMIN.toString())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "当前账户角色权限不足");
        }
        if (usersService.removeById(userId)) {
            log.info("删除用户成功");
            return ResultUtils.success("删除用户成功");
        } else {
            log.error("删除用户失败");
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR, "删除用户失败~用户或许不存在");
        }
    }

}
