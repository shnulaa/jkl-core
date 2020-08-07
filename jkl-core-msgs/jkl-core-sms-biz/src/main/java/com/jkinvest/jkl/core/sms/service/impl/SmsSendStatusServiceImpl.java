package com.jkinvest.jkl.core.sms.service.impl;


import org.springframework.stereotype.Service;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.core.sms.dao.SmsSendStatusMapper;
import com.jkinvest.jkl.core.sms.entity.SmsSendStatus;
import com.jkinvest.jkl.core.sms.service.SmsSendStatusService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service

public class SmsSendStatusServiceImpl extends SuperServiceImpl<SmsSendStatusMapper, SmsSendStatus> implements SmsSendStatusService {

}
