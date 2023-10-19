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
    public static final String APPID = "cdae0af6";
    /**
     * apiSecret
     */
    public static final String API_SECRET = "ZGQzMDU1NWJkNTUxOGI3NjNmZmFkOTRk";
    /**
     * apiKey
     */
    public static final String API_KEY = "bc2bab52cc86f8286258c34cd59889b6";
}
