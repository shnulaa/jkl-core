package com.jkinvest.jkl.core.sms.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.controller.QueryController;
import com.jkinvest.jkl.base.base.controller.SuperSimpleController;
import com.jkinvest.jkl.core.sms.entity.SmsSendStatus;
import com.jkinvest.jkl.core.sms.service.SmsSendStatusService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsSendStatus")
@Api(value = "SmsSendStatus", tags = "短信发送状态")
public class SmsSendStatusController extends SuperSimpleController<SmsSendStatusService, SmsSendStatus>
        implements QueryController<SmsSendStatus, Long, SmsSendStatus> {

}
