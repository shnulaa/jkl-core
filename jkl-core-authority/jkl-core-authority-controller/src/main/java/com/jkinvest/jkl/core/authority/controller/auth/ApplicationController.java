package com.jkinvest.jkl.core.authority.controller.auth;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.controller.SuperController;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.core.authority.dto.auth.ApplicationPageDTO;
import com.jkinvest.jkl.core.authority.dto.auth.ApplicationSaveDTO;
import com.jkinvest.jkl.core.authority.dto.auth.ApplicationUpdateDTO;
import com.jkinvest.jkl.core.authority.entity.auth.Application;
import com.jkinvest.jkl.core.authority.service.auth.ApplicationService;

import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", tags = "应用")
@PreAuth(replace = "application:")
public class ApplicationController extends SuperController<ApplicationService, Long, Application, ApplicationPageDTO, ApplicationSaveDTO, ApplicationUpdateDTO> {

    @Override
    public R<Application> handlerSave(ApplicationSaveDTO applicationSaveDTO) {
        applicationSaveDTO.setClientId(RandomUtil.randomString(24));
        applicationSaveDTO.setClientSecret(RandomUtil.randomString(32));
        return super.handlerSave(applicationSaveDTO);
    }

}
