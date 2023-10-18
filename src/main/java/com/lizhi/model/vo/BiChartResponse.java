package com.lizhi.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 图表VO
 * @data 2023 2023/10/11 16:11
 */
@Data
public class BiChartResponse implements Serializable {
    /**
     * 主键id
     */
    private Long chartId;
    /**
     * 目标
     */
    private String chartGoal;

    /**
     * 名称
     */
    private String chartName;

    /**
     * 内容
     */
    private String chartText;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 状态
     */
    private String chartStatus;

    /**
     * 代码
     */
    private String chartGen;

    /**
     * 分析结论
     */
    private String chartResult;



}
