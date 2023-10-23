package com.lizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizhi.service.UsersInterfaceInfoService;
import com.lizhi.mapper.UsersInterfaceInfoMapper;
import com.lizhicommen.entity.UsersInterfaceInfo;
import org.springframework.stereotype.Service;

/**
* @author liang
* @description 针对表【users_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2023-10-21 16:45:56
*/
@Service
public class UsersInterfaceInfoServiceImpl extends ServiceImpl<UsersInterfaceInfoMapper, UsersInterfaceInfo>
    implements UsersInterfaceInfoService{

}




