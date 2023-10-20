package com.lizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizhi.model.entity.LeaveMessage;
import com.lizhi.service.LeaveMessageService;
import com.lizhi.mapper.LeaveMessageMapper;
import com.lizhi.utils.ContentFilterUtils;
import org.springframework.stereotype.Service;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【leave_message(留言板)】的数据库操作Service实现
* @createDate 2023-10-20 14:07:31
*/
@Service
public class LeaveMessageServiceImpl extends ServiceImpl<LeaveMessageMapper, LeaveMessage>
    implements LeaveMessageService{

    @Override
    public void leaveMessageFilter(String message) {
        ContentFilterUtils.checkContent(message);
    }
}




