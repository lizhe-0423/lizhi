package com.lizhi.controller;

import com.lizhi.common.BaseResponse;
import com.lizhi.common.ResultUtils;
import com.lizhi.xhmodel.BigModelNew;
import com.lizhi.xhmodel.XhAiManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description
 * @data 2023 2023/10/19 15:06
 */
@Slf4j
@RequestMapping("/xh")
@RestController
public class XhChat {
    @Resource
    XhAiManage xhAiManage;

    /**
     * 星火模型发送消息
     * @param message 消息内容
     * @return 响应
     */
    @PostMapping("/chat")
    public BaseResponse<String> getChat(@RequestParam String message) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String sendMessage = xhAiManage.sendMessage(message);
        return ResultUtils.success(sendMessage);
    }
    @PostMapping("/chatByWebSocket")
    public BaseResponse<String> getChatByWebSocket(@RequestParam String message) throws Exception {
        BigModelNew bigModelNew = new BigModelNew("1", false);
        return ResultUtils.success(bigModelNew.getWebSocket(message));
    }
}
