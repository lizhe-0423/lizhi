package com.lizhi.manage;

import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhisdk.client.LizhiClient;
import com.lizhisdk.common.BaseResponse;
import com.lizhisdk.model.DevChatRequest;
import com.lizhisdk.model.DevChatResponse;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 */
@Service
public class AiManage {
    @Resource
    private LizhiClient lizhiClient;

    /**
     * AI 对话
     *
     * @param modelId 模型id
     * @param message 聊天内容
     * @param userAccessKey 系统分配的 accessKey
     * @param userSecretKey 系统分配的 secretKey
     * @return 消息回复内容
     */

    public String doChat(long modelId, String message, String userAccessKey, String userSecretKey) {
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(message);
        devChatRequest.setUserSecretKey(userSecretKey);
        devChatRequest.setUserAccessKey(userAccessKey);
        BaseResponse<DevChatResponse> response = lizhiClient.doChat(devChatRequest);
        if (response == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI 响应错误");
        }
        return response.getData().getContent();
    }
}
