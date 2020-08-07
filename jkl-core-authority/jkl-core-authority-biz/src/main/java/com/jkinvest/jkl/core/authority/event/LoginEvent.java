package com.jkinvest.jkl.core.authority.event;

import org.springframework.context.ApplicationEvent;

import com.jkinvest.jkl.core.authority.event.model.LoginStatusDTO;

/**
 * 登录事件
 *
 * @author zuihou
 * @date 2020年03月18日17:22:55
 */
public class LoginEvent extends ApplicationEvent {
    public LoginEvent(LoginStatusDTO source) {
        super(source);
    }
}