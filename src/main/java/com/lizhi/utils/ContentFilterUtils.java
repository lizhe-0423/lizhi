package com.lizhi.utils;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 *
 * @description 违禁内容检测工具包
 * @data 2023 2023/10/20 14:59
 */
public class ContentFilterUtils {

    private ContentFilterUtils(){
        throw new IllegalStateException("ContentFilterUtils class");
    }

    public static void checkContent(String content) {
        // 这里列出了一些常见的违规违禁内容，可以根据需要进行修改和扩展
        String[] illegalWords = {"逼", "操", "死"};
        for (String word : illegalWords) {
            Pattern pattern = Pattern.compile(word);
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"当前字符串含有违禁词汇");
            }
        }
    }
}
