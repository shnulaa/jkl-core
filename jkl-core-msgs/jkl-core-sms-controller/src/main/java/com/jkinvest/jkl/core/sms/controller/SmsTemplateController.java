package com.jkinvest.jkl.core.sms.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.controller.SuperController;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.log.annotation.SysLog;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.base.utils.BeanPlusUtil;
import com.jkinvest.jkl.core.sms.dto.SmsTemplateSaveDTO;
import com.jkinvest.jkl.core.sms.dto.SmsTemplateUpdateDTO;
import com.jkinvest.jkl.core.sms.entity.SmsTemplate;
import com.jkinvest.jkl.core.sms.service.SmsTemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 短信模板
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsTemplate")
@Api(value = "SmsTemplate", tags = "短信模板")
@PreAuth(replace = "sms:template:")
public class SmsTemplateController extends SuperController<SmsTemplateService, Long, SmsTemplate, SmsTemplate, SmsTemplateSaveDTO, SmsTemplateUpdateDTO> {

    @Override
    public R<SmsTemplate> handlerSave(SmsTemplateSaveDTO data) {
        SmsTemplate smsTemplate = BeanPlusUtil.toBean(data, SmsTemplate.class);
        baseService.saveTemplate(smsTemplate);
        return success(smsTemplate);
    }

    @Override
    public R<SmsTemplate> handlerUpdate(SmsTemplateUpdateDTO model) {
        SmsTemplate smsTemplate = BeanPlusUtil.toBean(model, SmsTemplate.class);
        baseService.updateTemplate(smsTemplate);
        return success(smsTemplate);
    }

    @ApiOperation(value = "检测自定义编码是否存在", notes = "检测自定义编码是否存在")
    @GetMapping("/check")
    @SysLog("检测自定义编码是否存在")
    @PreAuth("hasPermit('{}view')")
    public R<Boolean> check(@RequestParam(value = "customCode") String customCode) {
        int count = baseService.count(Wraps.<SmsTemplate>lbQ().eq(SmsTemplate::getCustomCode, customCode));
        return success(count > 0);
    }


}
