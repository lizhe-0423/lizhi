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
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 */
@Service
public class AiManage {
    @Resource
    private LizhiClient yuCongMingClient;

    /**
     * AI 对话
     * @param modelId 模型id
     * @param message 聊天内容
     * @return 消息回复内容
     */

    public String doChat(long modelId,String message){
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(message);

        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        if(response==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"AI 响应错误");
        }
        return response.getData().getContent();
    }
}
