package com.jkinvest.jkl.core.live.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.exception.BizException;
import com.jkinvest.jkl.base.jwt.TokenUtil;
import com.jkinvest.jkl.base.jwt.model.AuthInfo;
import com.jkinvest.jkl.base.jwt.model.JwtUserInfo;
import com.jkinvest.jkl.base.jwt.utils.JwtUtil;
import com.jkinvest.jkl.core.authority.dto.auth.LoginParamDTO;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证Controller
 *
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/jwt")
@AllArgsConstructor
@Api(value = "jwt", tags = "jwt")
public class JwtController {

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping(value = "/createAuthInfo")
    public R<AuthInfo> createAuthInfo(@Validated @RequestBody LoginParamDTO login) throws BizException {
        String tenant = JwtUtil.base64Decoder(login.getTenant());
        log.info("tenant={}", tenant);

        JwtUserInfo userInfo = new JwtUserInfo(1234L, login.getAccount(), "张三");

        AuthInfo authInfo = tokenUtil.createAuthInfo(userInfo, null);
        return R.success(authInfo);
    }

    @GetMapping(value = "/getAuthInfo")
    public R<AuthInfo> getAuthInfo(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.getAuthInfo(token));
    }

    @GetMapping(value = "/parseRefreshToken")
    public R<AuthInfo> parseRefreshToken(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.parseRefreshToken(token));
    }

}
