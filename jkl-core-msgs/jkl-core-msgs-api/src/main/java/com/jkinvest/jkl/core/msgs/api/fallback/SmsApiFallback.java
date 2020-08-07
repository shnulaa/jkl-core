package com.jkinvest.jkl.core.msgs.api.fallback;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.msgs.api.SmsApi;
import com.jkinvest.jkl.core.sms.dto.SmsSendTaskDTO;
import com.jkinvest.jkl.core.sms.dto.VerificationCodeDTO;
import com.jkinvest.jkl.core.sms.entity.SmsTask;

/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Component
public class SmsApiFallback implements SmsApi {
    @Override
    public R<SmsTask> send(SmsSendTaskDTO smsTaskDTO) {
        return R.timeout();
    }

    @Override
    public R<Boolean> sendCode(VerificationCodeDTO data) {
        return R.timeout();
    }

    @Override
    public R<Boolean> verification(VerificationCodeDTO data) {
        return R.timeout();
    }
}
