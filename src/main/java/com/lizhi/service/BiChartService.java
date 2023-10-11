package com.lizhi.service;

import com.lizhi.model.entity.BiChart;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【bi_chart(BI图表表)】的数据库操作Service
* @createDate 2023-10-11 16:05:20
*/
public interface BiChartService extends IService<BiChart> {
    /**
     * 保存图表接口
     * @param chart 图表
     * @return BiChart
     */
    BiChart saveChart(BiChart chart, MultipartFile multipartFile);

    /**
     * 封装要chart发送的内容
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
}
