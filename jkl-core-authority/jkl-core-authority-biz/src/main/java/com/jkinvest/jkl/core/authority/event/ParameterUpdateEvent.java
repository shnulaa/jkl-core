package com.jkinvest.jkl.core.authority.event;

import org.springframework.context.ApplicationEvent;

import com.jkinvest.jkl.core.authority.event.model.ParameterUpdate;

/**
 * 登录事件
 *
 * @author zuihou
 * @date 2020年03月18日17:22:55
 */
public class ParameterUpdateEvent extends ApplicationEvent {
    public ParameterUpdateEvent(ParameterUpdate source) {
        super(source);
    }
}
