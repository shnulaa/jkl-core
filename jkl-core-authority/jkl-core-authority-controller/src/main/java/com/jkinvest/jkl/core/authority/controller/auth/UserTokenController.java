package com.jkinvest.jkl.core.authority.controller.auth;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.controller.SuperController;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.core.authority.dto.auth.UserTokenPageDTO;
import com.jkinvest.jkl.core.authority.dto.auth.UserTokenSaveDTO;
import com.jkinvest.jkl.core.authority.dto.auth.UserTokenUpdateDTO;
import com.jkinvest.jkl.core.authority.entity.auth.UserToken;
import com.jkinvest.jkl.core.authority.service.auth.UserTokenService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


/**
 * <p>
 * 前端控制器
 * token
 * </p>
 *
 * @author zuihou
 * @date 2020-04-02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/userToken")
@Api(value = "UserToken", tags = "token")
@PreAuth(replace = "userToken:")
public class UserTokenController extends SuperController<UserTokenService, Long, UserToken, UserTokenPageDTO, UserTokenSaveDTO, UserTokenUpdateDTO> {


}
