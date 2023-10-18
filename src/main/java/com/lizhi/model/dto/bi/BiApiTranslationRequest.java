package com.lizhi.model.dto.bi;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description bi翻译请求接口
 * @data 2023 2023/10/10 10:05
 */
@Data
public class BiApiTranslationRequest implements Serializable {
    private String text;
    private String type;
    private String style;
}
