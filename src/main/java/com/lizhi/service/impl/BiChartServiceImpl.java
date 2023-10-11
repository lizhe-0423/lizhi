package com.lizhi.service.impl;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizhi.common.ErrorCode;
import com.lizhi.constant.BiConstant;
import com.lizhi.manage.AiManage;
import com.lizhi.model.entity.BiChart;
import com.lizhi.service.BiChartService;
import com.lizhi.mapper.BiChartMapper;
import com.lizhi.utils.ExcelUtils;
import com.lizhi.utils.ThrowUtil;
import io.reactivex.rxjava3.core.Completable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【bi_chart(BI图表表)】的数据库操作Service实现
* @createDate 2023-10-11 16:05:20
*/
@Service
public class BiChartServiceImpl extends ServiceImpl<BiChartMapper, BiChart>
    implements BiChartService{

    @Resource
    AiManage aiManage;

    @Override
    public BiChart saveChart(BiChart chart,MultipartFile multipartFile) {
     String chartGoal = chart.getChartGoal();
     ThrowUtil.throwIf(CharSequenceUtil.isBlank(chartGoal), ErrorCode.NOT_FOUND_ERROR);
     String chartName = chart.getChartName();
     ThrowUtil.throwIf(CharSequenceUtil.isBlank(chartName), ErrorCode.NOT_FOUND_ERROR);
     String chartText = chart.getChartText();
     ThrowUtil.throwIf(CharSequenceUtil.isBlank(chartText), ErrorCode.NOT_FOUND_ERROR);
     String chartType = chart.getChartType();
     ThrowUtil.throwIf(CharSequenceUtil.isBlank(chartType), ErrorCode.NOT_FOUND_ERROR);
     String excelToCsv = ExcelUtils.excelToCsv(multipartFile);
        CompletableFuture.runAsync(()->{
            this.save(chart);
            String doChat = aiManage.doChat(BiConstant.CHART_ID, getChartMessage(chartGoal, chartType,excelToCsv));
            receiveChartMessage(doChat,chart);
        });
     // doChat 进行分割 取出 Result 和 代码
        return chart;
    }

    @Override
    public String getChartMessage(String userGoal, String chartType,String excelToCsv) {
        return "分析需求:"+"\n"+userGoal+"请使用"+chartType+"\n"+"原始数据"+"\n"+excelToCsv;
    }

    @Override
    public BiChart receiveChartMessage(String doChart, BiChart biChart) {
        return null;
    }
}




