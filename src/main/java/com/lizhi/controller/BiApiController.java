package com.lizhi.controller;
import cn.dev33.satoken.stp.StpUtil;
import com.lizhi.common.BaseResponse;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.common.ResultUtils;
import com.lizhi.model.dto.bi.BiApiSignatureRequest;
import com.lizhi.model.dto.bi.BiApiTranslationRequest;
import com.lizhi.model.entity.BiApi;
import com.lizhi.service.BiApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 智能Bi的一些通用接口
 * @data 2023 2023/10/9 17:34
 */
@RestController
@Slf4j
@RequestMapping("/biApi")
public class BiApiController {
    @Resource
    BiApiService biApiService;
    @PostMapping("/getSignature")
    public BaseResponse<BiApi> getSignature(@RequestBody BiApiSignatureRequest biApiSignatureRequest){
        if(!StpUtil.isLogin()){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"当前帐号未登录");
        }
        if(biApiSignatureRequest==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(biApiService.getSignature(biApiSignatureRequest));
    }
    @PostMapping("/getTranslation")
    public BaseResponse<BiApi> getTranslation(@RequestBody BiApiTranslationRequest biApiTranslationRequest){
        if(!StpUtil.isLogin()){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"当前帐号未登录");
        }
        if(biApiTranslationRequest==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(biApiService.getTranslation(biApiTranslationRequest));
    }
}
