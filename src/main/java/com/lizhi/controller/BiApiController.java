package com.lizhi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lizhi.common.BaseResponse;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.common.ResultUtils;
import com.lizhi.constant.LogConstant;
import com.lizhi.model.dto.bi.BiApiSignatureRequest;
import com.lizhi.model.dto.bi.BiApiTranslationRequest;
import com.lizhi.model.dto.bi.BiCopyRequest;
import com.lizhi.model.entity.BiApi;
import com.lizhi.service.BiApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
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
@EnableAsync
public class BiApiController {
    @Resource
    BiApiService biApiService;

    /**
     * 获取签名接口
     * @param biApiSignatureRequest 签名请求
     * @return BaseResponse<BiApi>
     */
    @PostMapping("/getSignature")
    public BaseResponse<BiApi> getSignature(@RequestBody BiApiSignatureRequest biApiSignatureRequest){
        if(!StpUtil.isLogin()){
            log.error(LogConstant.LOGERROR,ErrorCode.NOT_FOUND_ERROR,"获取getSignature账号未登录");
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if(biApiSignatureRequest==null){
            log.error(LogConstant.LOGERROR,ErrorCode.NOT_FOUND_ERROR,"未发现请求biApiSignatureRequest");
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(biApiService.getSignature(biApiSignatureRequest));
    }

    /**
     * 获取翻译接口
     * @param biApiTranslationRequest 翻译请求
     * @return BaseResponse<BiApi>
     */
    @PostMapping("/getTranslation")
    public BaseResponse<BiApi> getTranslation(@RequestBody BiApiTranslationRequest biApiTranslationRequest){
        if(!StpUtil.isLogin()){
            log.error(LogConstant.LOGERROR,ErrorCode.NOT_FOUND_ERROR,"获取getTranslation账号未登录");
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if(biApiTranslationRequest==null){
            log.error(LogConstant.LOGERROR,ErrorCode.NOT_FOUND_ERROR,"未发现请求biApiTranslationRequest");
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(biApiService.getTranslation(biApiTranslationRequest));
    }

    /**
     * 获取文案接口
     * @param biCopyRequest 文案请求
     * @return BaseResponse<BiApi>
     */
    @PostMapping("/getCopy")
    public BaseResponse<BiApi> getCopy(@RequestBody BiCopyRequest biCopyRequest){
        if(!StpUtil.isLogin()){
            log.error(LogConstant.LOGERROR,ErrorCode.NOT_FOUND_ERROR,"获取biCopyRequest账号未登录");
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if(biCopyRequest==null){
            log.error(LogConstant.LOGERROR,ErrorCode.NOT_FOUND_ERROR,"未发现请求biCopyRequest");
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(biApiService.getCopy(biCopyRequest));
    }
}
