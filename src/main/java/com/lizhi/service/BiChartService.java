package com.lizhi.service;

import com.lizhi.model.dto.chart.ChartAddRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lizhicommen.entity.BiChart;
import org.springframework.web.multipart.MultipartFile;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【bi_chart(BI图表表)】的数据库操作Service
* @createDate 2023-10-11 16:05:20
*/
public interface BiChartService extends IService<BiChart> {
    /**
     * 保存图表接口
     * @param chartAddRequest 图表新增请求
     * @param multipartFile 文件
     * @return BiChart
     */
    BiChart saveChart(ChartAddRequest chartAddRequest, MultipartFile multipartFile);

    /**
     * 封装要发送的chart内容
     * @param userGoal 分析目标
     * @param chartType 分析类型
     * @param excelToCsv excel文件
     * @return String
     */
    String getChartMessage(String userGoal,String chartType,String excelToCsv);

    /**
     * 接收要chart发送完成后的内容并进行分割保存处理
     * @param doChart 接收的内容
     * @param biChart 需要保存的biChart实体
     * @return BiChart
     */
    BiChart receiveChartMessage(String doChart,BiChart biChart);

    /**
     * 更新图表失败
     * @param chart 传入的图表类
     * @param execMessage 传入的执行信息
     */
    void handleChartUpdateError(BiChart chart,String execMessage);

    /**
     * 通过MQ保存图表接口
     * @param chartAddRequest 图标请求
     * @param multipartFile 文件
     * @return BiChart
     */
    BiChart saveChartByMq(ChartAddRequest chartAddRequest, MultipartFile multipartFile);
}
