package com.lizhi.xhmodel;

import cn.dev33.satoken.stp.StpUtil;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.model.entity.AiMessage;
import com.lizhi.service.AiMessageService;
import com.lizhi.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 * @name lizhi
 * @description
 * @data 2023 2023/10/23 9:48
 */
@Service
public class XhAiManage {

    @Resource
    UsersService usersService;
    @Resource
    AiMessageService aiMessageService;
    /**
     * 星火模型发送消息
     * @param message 接收发送的消息内容
     * @return String 返回响应内容
     */
     public AiMessage sendMessage(String message) throws Exception {
        // 获取当前用户id
        Long userByCurrent = usersService.getUserByCurrent();
        BigModelNew bigModelNew = new BigModelNew(userByCurrent.toString(),false);
        AiMessage aiMessage = new AiMessage();
        aiMessage.setUserId(userByCurrent);
        aiMessage.setLogo(1);
        aiMessage.setContent(message);
        aiMessage.setSerialNumber(0L);
        aiMessageService.save(aiMessage);
        bigModelNew.bigModelMain(message);
        return aiMessage;
    }
}
