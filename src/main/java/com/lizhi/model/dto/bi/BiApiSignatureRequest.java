package com.lizhi.model.dto.bi;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description bi 签名请求
 * @data 2023 2023/10/9 17:38
 */
@Data
public class BiApiSignatureRequest implements Serializable {
    private String words;
    private String type;
    private String style;
}
