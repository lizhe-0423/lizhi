package com.lizhi.service;

import com.lizhi.model.entity.LeaveMessage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【leave_message(留言板)】的数据库操作Service
* @createDate 2023-10-20 14:07:31
*/
public interface LeaveMessageService extends IService<LeaveMessage> {

    /**
     * 敏感留言过滤
     * @param message 留言内容
     */
    void leaveMessageFilter(String message);
}
