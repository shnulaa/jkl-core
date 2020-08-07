package com.jkinvest.jkl.core.authority.controller.common;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.controller.SuperController;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.core.authority.dto.common.ParameterPageDTO;
import com.jkinvest.jkl.core.authority.dto.common.ParameterSaveDTO;
import com.jkinvest.jkl.core.authority.dto.common.ParameterUpdateDTO;
import com.jkinvest.jkl.core.authority.entity.common.Parameter;
import com.jkinvest.jkl.core.authority.service.common.ParameterService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @date 2020-02-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/parameter")
@Api(value = "Parameter", tags = "参数配置")
@PreAuth(replace = "parameter:")
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageDTO, ParameterSaveDTO, ParameterUpdateDTO> {

}
