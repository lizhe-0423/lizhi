package com.lizhi.controller;
import cn.hutool.core.bean.BeanUtil;
import com.lizhi.common.BaseResponse;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.common.ResultUtils;
import com.lizhi.constant.LogConstant;
import com.lizhi.model.dto.chart.ChartAddRequest;
import com.lizhi.model.vo.BiChartResponse;
import com.lizhi.service.BiChartService;
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

    /**
     * 生成图表功能接口
     * @param chartAddRequest 图表请求
     * @param multipartFile  要处理的文件
     * @return BaseResponse<BiChartResponse>
     */
    @PostMapping("/gen")
    public BaseResponse<BiChartResponse> genChart(@RequestPart("file") MultipartFile multipartFile,ChartAddRequest chartAddRequest){
        if(chartAddRequest==null){
            log.error(LogConstant.LOGERROR, ErrorCode.NOT_FOUND_ERROR,"未发现请求chartAddRequest");
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        BiChartResponse biChartResponse = new BiChartResponse();
        BeanUtil.copyProperties(biChartService.saveChart(chartAddRequest,multipartFile),biChartResponse);
        return ResultUtils.success(biChartResponse);
    }
}
