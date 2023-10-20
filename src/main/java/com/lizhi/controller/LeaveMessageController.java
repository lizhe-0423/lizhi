package com.lizhi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lizhi.common.BaseResponse;
import com.lizhi.common.ErrorCode;
import com.lizhi.common.ResultUtils;
import com.lizhi.model.entity.LeaveMessage;
import com.lizhi.service.LeaveMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 留言板接口
 * @data 2023 2023/10/20 14:08
 */
@RestController
@RequestMapping("/leaveMessage")
@Slf4j
public class LeaveMessageController {
    @Resource
    LeaveMessageService leaveMessageService;

    /**
     * 新增留言
     * @param message 留言内容
     * @return BaseResponse<String>
     */
    @PostMapping("/add")
    public BaseResponse<String> addLeaveMessage(@RequestParam String message){
        if(StringUtils.isEmpty(message))
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setMessage(message);
        leaveMessage.setUserId(Long.parseLong((String) StpUtil.getLoginId()));
        if(leaveMessageService.save(leaveMessage)) {
            log.info("新增留言成功");
            return ResultUtils.success("留言新增成功");
        }
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"留言新增失败");
    }

    /**
     * 获取留言集合
     * @return BaseResponse<List<LeaveMessage>>
     */
    @PostMapping("/list")
    public BaseResponse<List<LeaveMessage>> getLeaveMessageByList(){
        List<LeaveMessage> list = leaveMessageService.list();
        return ResultUtils.success(list);
    }
}
