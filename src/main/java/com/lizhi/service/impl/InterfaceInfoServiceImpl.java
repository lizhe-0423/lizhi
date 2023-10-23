package com.lizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizhi.service.InterfaceInfoService;
import com.lizhi.mapper.InterfaceInfoMapper;
import com.lizhicommen.entity.InterfaceInfo;
import org.springframework.stereotype.Service;

/**
* @author liang
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-10-21 16:45:51
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

}




