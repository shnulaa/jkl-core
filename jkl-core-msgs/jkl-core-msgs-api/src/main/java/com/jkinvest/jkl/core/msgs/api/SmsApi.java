package com.jkinvest.jkl.core.msgs.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.entity.SuperEntity;
import com.jkinvest.jkl.core.msgs.api.fallback.SmsApiFallback;
import com.jkinvest.jkl.core.sms.dto.SmsSendTaskDTO;
import com.jkinvest.jkl.core.sms.dto.VerificationCodeDTO;
import com.jkinvest.jkl.core.sms.entity.SmsTask;

/**
 * 文件接口
 *
 * @author zuihou
 * @date 2019/06/21
 */
@FeignClient(name = "${zuihou.feign.msgs-server:zuihou-msgs-server}", fallback = SmsApiFallback.class)
public interface SmsApi {
    /**
     * 短信发送
     *
     * @param smsTaskDTO
     * @return
     */
    @RequestMapping(value = "/smsTask/send", method = RequestMethod.POST)
    R<SmsTask> send(@RequestBody SmsSendTaskDTO smsTaskDTO);

    /**
     * 发送验证码
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/verification/send")
    R<Boolean> sendCode(@Validated @RequestBody VerificationCodeDTO data);

    /**
     * 验证
     *
     * @param data
     * @return
     */
    @PostMapping("/verification")
    R<Boolean> verification(@Validated(SuperEntity.Update.class) @RequestBody VerificationCodeDTO data);
}
