package com.jkinvest.jkl.core.authority.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.core.authority.event.ParameterUpdateEvent;
import com.jkinvest.jkl.core.authority.event.model.ParameterUpdate;
import com.jkinvest.jkl.core.authority.service.auth.UserTokenService;
import com.jkinvest.jkl.core.common.constant.ParameterKey;

import lombok.extern.slf4j.Slf4j;

/**
 * 参数修改事件监听，用于调整具体的业务
 *
 * @author zuihou
 * @date 2020年03月18日17:39:59
 */
@Component
@Slf4j
public class ParameterUpdateListener {

    @Autowired
    private UserTokenService userTokenService;

    @Async
    @EventListener({ParameterUpdateEvent.class})
    public void saveSysLog(ParameterUpdateEvent event) {
        ParameterUpdate source = (ParameterUpdate) event.getSource();

        BaseContextHandler.setTenant(source.getTenant());
        if (ParameterKey.LOGIN_POLICY.equals(source.getKey())) {
            userTokenService.reset();
        }
    }
}
