package com.lizhi.service.impl;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.constant.BiConstant;
import com.lizhi.manage.AiManage;
import com.lizhi.mapper.BiApiMapper;
import com.lizhi.model.dto.bi.BiApiSignatureRequest;
import com.lizhi.model.dto.bi.BiApiTranslationRequest;
import com.lizhi.model.entity.BiApi;
import com.lizhi.model.entity.Users;
import com.lizhi.service.BiApiService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* @author <a href="https://github.com/lizhe-0423">lizhi</a>
* @description 针对表【bi_api(基于BI的API功能表)】的数据库操作Service实现
* @createDate 2023-10-10 14:13:47
*/
@Service
public class BiApiServiceImpl extends ServiceImpl<BiApiMapper, BiApi>
    implements BiApiService{

    @Resource
    AiManage aiManage;
    @Override
    public BiApi getSignature(BiApiSignatureRequest biApiSignatureRequest) {
        Users user =(Users) StpUtil.getSession().get("user");
        Long signatureId = BiConstant.SIGNATURE_ID;
        String type = biApiSignatureRequest.getType();
        String style = biApiSignatureRequest.getStyle();
        String words = biApiSignatureRequest.getWords();
        String str="请帮我生成一个个性签名 类型:"+type+"风格:"+style+"字数:"+words;
        BiApi biApi = new BiApi();
        biApi.setBiId(IdUtil.simpleUUID());
        biApi.setUserId(user.getUserId());
        biApi.setBiName("签名");
        biApi.setBiType(type);
        biApi.setBiStyle(style);
        isParameterCheck(biApi);
        biApi.setCreateTime(DateTime.now());
        String doChat = aiManage.doChat(signatureId, str);
        biApi.setBiContent(doChat);
        this.save(biApi);
        return biApi;
    }
    @Override
    public BiApi getTranslation(BiApiTranslationRequest biApiTranslationRequest) {
        Users user =(Users) StpUtil.getSession().get("user");
        Long translationId = BiConstant.TRANSLATION_ID;
        String text = biApiTranslationRequest.getText();
        String type = biApiTranslationRequest.getType();
        String style = biApiTranslationRequest.getStyle();
        String str="请帮我翻译以下内容 类型(决定翻译成什么语言):"+type+"风格:"+style+"内容:"+text;
        BiApi biApi = new BiApi();
        biApi.setBiId(IdUtil.simpleUUID());
        biApi.setUserId(user.getUserId());
        biApi.setBiName("翻译");
        biApi.setBiType(type);
        biApi.setBiStyle(style);
        biApi.setCreateTime(DateTime.now());
        isParameterCheck(biApi);
        String doChat = aiManage.doChat(translationId, str);
        biApi.setBiContent(doChat);
        this.save(biApi);
        return biApi;
    }

    @Override
    public void isParameterCheck(BiApi biApi) {
        if(CharSequenceUtil.isBlank(biApi.getBiType())){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if(CharSequenceUtil.isBlank(biApi.getBiStyle())){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
    }

}




