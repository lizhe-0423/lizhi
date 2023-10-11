package com.lizhi.model.dto.chart;

import lombok.Data;

import java.io.Serializable;

/**
 * 调用AI接口请求
 *
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 * 
 */
@Data
public class GenChartByAiRequest implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 名称
     */
    private String goal;

    /**
     * 图表类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}