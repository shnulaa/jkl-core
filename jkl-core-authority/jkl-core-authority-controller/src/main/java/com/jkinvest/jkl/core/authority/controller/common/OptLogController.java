package com.jkinvest.jkl.core.authority.controller.common;


import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.controller.SuperController;
import com.jkinvest.jkl.base.base.request.PageParams;
import com.jkinvest.jkl.base.log.annotation.SysLog;
import com.jkinvest.jkl.base.log.entity.OptLogDTO;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.core.authority.dto.common.OptLogUpdateDTO;
import com.jkinvest.jkl.core.authority.entity.common.OptLog;
import com.jkinvest.jkl.core.authority.service.common.OptLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Api(value = "OptLog", tags = "系统日志")
@PreAuth(replace = "optLog:")
public class OptLogController extends SuperController<OptLogService, Long, OptLog, OptLog, OptLogDTO, OptLogUpdateDTO> {

    @Override
    @ApiOperation(value = "分页列表查询")
    @PostMapping(value = "/page")
    @PreAuth("hasPermit('{}view')")
    public R<IPage<OptLog>> page(@RequestBody @Validated PageParams<OptLog> params) {
        return super.page(params);
    }

    @ApiOperation("清空日志")
    @DeleteMapping("clear")
    @SysLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = 0;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            clearBeforeNum = 1000;        // 清理一千条以前日志数据
        } else if (type == 6) {
            clearBeforeNum = 10000;        // 清理一万条以前日志数据
        } else if (type == 7) {
            clearBeforeNum = 30000;        // 清理三万条以前日志数据
        } else if (type == 8) {
            clearBeforeNum = 100000;    // 清理十万条以前日志数据
        } else if (type == 9) {
            clearBeforeNum = null;            // 清理所有日志数据
        } else {
            return R.validFail("参数错误");
        }

        return success(baseService.clearLog(clearBeforeTime, clearBeforeNum));
    }

}
