package com.lizhi.model.dto.chart;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 * 
 */
@Data
public class ChartAddRequest implements Serializable {

    /**
     * 名称
     */
    private String chartName;

    /**
     * 分析目标
     */
    private String chartGoal;

    /**
     * 图表类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}