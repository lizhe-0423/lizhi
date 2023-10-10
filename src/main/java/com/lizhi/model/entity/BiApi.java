package com.lizhi.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 基于BI的API功能表
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 * @TableName bi_api
 */
@TableName(value ="bi_api")
@Data
public class BiApi implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String biId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 业务名称
     */
    private String biName;

    /**
     * 业务类型
     */
    private String biType;

    /**
     * 风格主题
     */
    private String biStyle;

    /**
     * 内容
     */
    private String biContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 逻辑删除 0 未删除 1 删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}