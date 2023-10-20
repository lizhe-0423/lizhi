package com.lizhi.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.constant.CommonConstant;
import com.lizhi.constant.UserConstant;
import com.lizhi.mapper.UsersMapper;
import com.lizhi.model.dto.user.UserByIdRequest;
import com.lizhi.model.dto.user.UserSearchRequest;
import com.lizhi.model.entity.Users;
import com.lizhi.service.UsersService;
import com.lizhi.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【users】的数据库操作Service实现
* @createDate 2023-10-07 17:45:20
*/
@Service
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService {


    @Override
    public Long userRegister(String userAccount, String userPassword) {
        if(userAccount==null||userAccount.length()<CommonConstant.FIELD_MAX.getFieldMax()
                ||userPassword==null||userPassword.length()<CommonConstant.FIELD_MAX.getFieldMax()){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"请输入正确的用户名或密码");
        }
        LambdaQueryWrapper<Users> usersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        usersLambdaQueryWrapper.eq(Users::getUserAccount,userAccount);
        Users userQuery = this.getOne(usersLambdaQueryWrapper);
        if(userQuery!=null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据库已存在当前记录");
        }
        Users user = createUser(userAccount, userPassword);
        this.save(user);
        return user.getId();
    }
    @Override
    public Users createUser(String userAccount, String userPassword) {
        Users users = new Users();
        users.setUserName("荔枝");
        users.setUserAccount(userAccount);
        users.setUserPassword(DigestUtil.md5Hex(userPassword));
        users.setUpdateTime(DateUtil.date());
        users.setDeleteTime(DateUtil.date());
        users.setUserLevel(StrUtil.toString(UserConstant.USER));
        users.setUserPhoto("默认图片");
        users.setIsDelete(0);
        return users;
    }


    @Override
    public Users getLoginUser(String userAccount, String userPassword) {
        LambdaQueryWrapper<Users> usersLambdaQueryWrapperByAcAndPw = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Users> usersLambdaQueryWrapperByAc = new LambdaQueryWrapper<>();
        usersLambdaQueryWrapperByAcAndPw.eq(Users::getUserAccount,userAccount)
                .eq(Users::getUserPassword,DigestUtil.md5Hex(userPassword));
        usersLambdaQueryWrapperByAc.eq(Users::getUserAccount,userAccount);
        Users userQueryByAcAndPw = this.getOne(usersLambdaQueryWrapperByAcAndPw);
        Users userQueryByAc = this.getOne(usersLambdaQueryWrapperByAc);
        if(userQueryByAc==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"用户账号不存在");
        }else if(userQueryByAcAndPw==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"用户账号或者用户密码错误");
        }
        return userQueryByAcAndPw;
    }


    @Override
    public QueryWrapper<Users> getQueryWrapper(UserSearchRequest userSearchRequest) {
         Long userId = userSearchRequest.getUserId();
         String userName = userSearchRequest.getUserName();
         String userPhoto = userSearchRequest.getUserPhoto();
         String userLevel = userSearchRequest.getUserLevel();
         String sortField = userSearchRequest.getSortField();
         String sortOrder = userSearchRequest.getSortOrder();
         QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId!=null,"user_id",userId);
        queryWrapper.like(CharSequenceUtil.isNotBlank(userName),"user_name",userName);
        queryWrapper.eq(userPhoto!=null,"user_photo",userPhoto);
        queryWrapper.eq(CharSequenceUtil.isNotBlank(userLevel),"user_level",userLevel);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.PAGE_SORT_ORDER_ASC.toString()), sortField);
        return queryWrapper;
    }
    @Override
    public Long isRoleGetUserById(UserByIdRequest userByIdRequest) {
        if(userByIdRequest==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"用户id不存在");
        }
        Long userId = userByIdRequest.getUserId();
        List<String> roleList = StpUtil.getRoleList();
        log.info("当前用户角色为{}",roleList);
        return userId;
    }
}




