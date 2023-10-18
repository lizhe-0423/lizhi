package com.lizhi.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * BI图表表
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 * @TableName bi_chart
 */
@TableName(value ="bi_chart")
@Data
public class BiChart implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}