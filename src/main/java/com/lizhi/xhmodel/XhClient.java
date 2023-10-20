package com.lizhi.xhmodel;
import lombok.Getter;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 星火大模型设置
 * @data 2023 2023/10/19 11:31
 */
@Getter
public class XhClient {

    private XhClient() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * host url
     */
    public static final String HOST_URL = "https://spark-api.xf-yun.com/v2.1/chat";
    /**
     * appid
     */
    public static final String APPID = "";
    /**
     * apiSecret
     */
    public static final String API_SECRET = "";
    /**
     * apiKey
     */
    public static final String API_KEY = "";
}
