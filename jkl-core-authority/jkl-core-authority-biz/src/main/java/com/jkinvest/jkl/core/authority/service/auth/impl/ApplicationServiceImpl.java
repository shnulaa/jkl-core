package com.jkinvest.jkl.core.authority.service.auth.impl;


import org.springframework.stereotype.Service;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.core.authority.dao.auth.ApplicationMapper;
import com.jkinvest.jkl.core.authority.entity.auth.Application;
import com.jkinvest.jkl.core.authority.service.auth.ApplicationService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Service

public class ApplicationServiceImpl extends SuperServiceImpl<ApplicationMapper, Application> implements ApplicationService {

}
