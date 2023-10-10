package com.lizhi.model.dto.bi;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 生成文案请求
 * @data 2023 2023/10/10 16:06
 */
@Data
public class BiCopyRequest implements Serializable {
    private String text;
    private String type;
    private String style;
}
