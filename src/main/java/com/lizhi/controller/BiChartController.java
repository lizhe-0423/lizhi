package com.lizhi.controller;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.lizhi.common.BaseResponse;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.common.ResultUtils;
import com.lizhi.constant.LogConstant;
import com.lizhi.constant.UserConstant;
import com.lizhi.manage.RedisLimitManager;
import com.lizhi.model.dto.chart.ChartAddRequest;
import com.lizhi.model.vo.BiChartResponse;
import com.lizhi.service.BiChartService;
import com.lizhi.utils.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 关于图表的一些接口
 * @data 2023 2023/10/11 16:10
 */
@RestController
@RequestMapping("/chart")
@Slf4j
public class BiChartController {
    @Resource
    BiChartService biChartService;
    @Resource
    RedisLimitManager redisLimitManager;

    /**
     * 生成图表功能接口
     * @param chartAddRequest 图表请求
     * @param multipartFile  要处理的文件
     * @return BaseResponse<BiChartResponse>
     */
    @PostMapping("/gen")
    public BaseResponse<BiChartResponse> genChart(@RequestPart("file") MultipartFile multipartFile,ChartAddRequest chartAddRequest){
        validLogin(chartAddRequest);
        BiChartResponse biChartResponse = new BiChartResponse();
        if(UserConstant.USER.toString().equals(validLogin(chartAddRequest))){
            redisLimitManager.doRateLimitByUser("XL:USER:"+StpUtil.getSession().get("user"));
            BeanUtil.copyProperties(biChartService.saveChart(chartAddRequest,multipartFile),biChartResponse);
            return ResultUtils.success(biChartResponse);
        }else if(UserConstant.VIP.toString().equals(validLogin(chartAddRequest))){
            redisLimitManager.doRateLimitByVip("XL:VIP:"+StpUtil.getSession().get("user"));
            BeanUtil.copyProperties(biChartService.saveChart(chartAddRequest,multipartFile),biChartResponse);
            return ResultUtils.success(biChartResponse);
        }
       return ResultUtils.success(biChartResponse);
    }

    /**
     * 通过MQ生成图表接口功能
     * @param multipartFile 要处理的文件
     * @param chartAddRequest 图标请求
     * @return BaseResponse<BiChartResponse>
     */
    @PostMapping("/genMQ")
    public BaseResponse<BiChartResponse> genChartByMq(@RequestPart("file") MultipartFile multipartFile,ChartAddRequest chartAddRequest){
        /*
         这是通过MQ实现图表生成功能
         */
        BiChartResponse biChartResponse = new BiChartResponse();
        if(UserConstant.USER.toString().equals(validLogin(chartAddRequest))){
            redisLimitManager.doRateLimitByUser("XL:USER:"+StpUtil.getSession().get("user"));
            BeanUtil.copyProperties(biChartService.saveChartByMq(chartAddRequest,multipartFile),biChartResponse);
            return ResultUtils.success(biChartResponse);
        }else if(UserConstant.VIP.toString().equals(validLogin(chartAddRequest))){
            redisLimitManager.doRateLimitByVip("XL:VIP:"+StpUtil.getSession().get("user"));
            BeanUtil.copyProperties(biChartService.saveChartByMq(chartAddRequest,multipartFile),biChartResponse);
            return ResultUtils.success(biChartResponse);
        }
        return ResultUtils.success(biChartResponse);
    }

    /**
     * 检验用户是否登录是否具有获取参数权限
     * @param chartAddRequest http请求
     * @return 用户当前等级
     */
    public String validLogin(ChartAddRequest chartAddRequest){
        if (!StpUtil.isLogin()) {
            log.error(LogConstant.LOGERROR,ErrorCode.NOT_FOUND_ERROR,"获取chartAddRequest账号未登录");
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (chartAddRequest == null) {
            log.error(LogConstant.LOGERROR, ErrorCode.NOT_FOUND_ERROR,"未发现请求chartAddRequest");
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        ThrowUtil.throwIf(!StpUtil.hasRoleOr("USER","ADMIN"),ErrorCode.NO_AUTH_ERROR);
        if (StpUtil.hasRole(UserConstant.VIP.toString())) {
            return UserConstant.VIP.toString();
        }else if (StpUtil.hasRole(UserConstant.USER.toString())) {
            return UserConstant.USER.toString();
        }
        log.debug("当前用户或许已被封禁");
        return "BAN";
    }
}
