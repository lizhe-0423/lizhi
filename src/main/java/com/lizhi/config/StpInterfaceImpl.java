package com.lizhi.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhicommen.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝程序呀</a>
 */
@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();
        Users user = (Users) StpUtil.getSession().get("user");
        if(user==null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"当前账号为登录");
        }
        log.info("当前user session:{}",user);
        list.add(user.getUserLevel());
        return list;
    }

}
