package com.lizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizhi.model.dto.bi.BiApiSignatureRequest;
import com.lizhi.model.dto.bi.BiApiTranslationRequest;
import com.lizhi.model.entity.BiApi;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【bi_api(基于BI的API功能表)】的数据库操作Service
* @createDate 2023-10-10 14:13:47
*/
public interface BiApiService extends IService<BiApi> {
    /**
     * 得到bi签名
     * @param biApiSignatureRequest bi签名请求
     * @return 签名内容
     */
    BiApi getSignature(BiApiSignatureRequest biApiSignatureRequest);

    /**
     * 得到bi翻译
     * @param biApiTranslationRequest bi翻译请求
     * @return 翻译内容
     */
    BiApi getTranslation(BiApiTranslationRequest biApiTranslationRequest);

    /**
     * 检验参数是否合法
     * @param biApi biApi类中参数
     */
    void isParameterCheck(BiApi biApi);
}
