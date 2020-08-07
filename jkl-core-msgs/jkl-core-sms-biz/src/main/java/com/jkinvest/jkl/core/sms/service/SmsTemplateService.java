package com.jkinvest.jkl.core.sms.service;

import com.jkinvest.jkl.base.base.service.SuperService;
import com.jkinvest.jkl.core.sms.entity.SmsTemplate;

/**
 * <p>
 * 业务接口
 * 短信模板
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
public interface SmsTemplateService extends SuperService<SmsTemplate> {
    /**
     * 保存模板，并且将模板内容解析成json格式
     *
     * @param smsTemplate
     * @return
     * @author zuihou
     * @date 2019-05-16 21:13
     */
    void saveTemplate(SmsTemplate smsTemplate);

    /**
     * 修改
     *
     * @param smsTemplate
     */
    void updateTemplate(SmsTemplate smsTemplate);
}
