package com.lizhi.xhmodel;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.lizhi.xhmodel.XhClient.*;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 星火ai大模型
 * @data 2023 2023/10/19 11:34
 */
@Service
@Slf4j
public class XhAiManage {
    /**
     * 发送消息
     * @param message 消息内容
     * @return 响应内容
     */
    public String sendMessage(String message) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String url = getAuthUrl(HOST_URL, API_KEY, API_SECRET);
        Request request = new Request.Builder().url(url).build();
        return sendRequest(request,message);
    }

    public String sendRequest(Request request,String message) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, message);
        request = request.newBuilder().post(body).build();
        Response response = client.newCall(request).execute();
        if (response.body() != null) {
            return response.body().string();
        }
        log.error("星火大模型发送请求出现异常{}内容为{}",request,message);
        return "出现异常";
    }


    /**
     * url 鉴权
     * @param hostUrl 主机url
     * @param apiKey apiKey
     * @param apiSecret apiSecret
     * @return url
     */
    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws MalformedURLException, NoSuchAlgorithmException, InvalidKeyException {
        URL url = new URL(hostUrl);
        // 时间
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        // 拼接
        String preStr = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        // SHA256加密
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);

        byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
        // Base64加密
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        // 拼接
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        // 拼接地址
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder()
                .addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8)))
                .addQueryParameter("date", date)
                .addQueryParameter("host", url.getHost())
                .build();
        return httpUrl.toString();
    }
}
