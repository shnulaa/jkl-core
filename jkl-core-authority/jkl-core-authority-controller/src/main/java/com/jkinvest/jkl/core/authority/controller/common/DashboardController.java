package com.jkinvest.jkl.core.authority.controller.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.database.properties.DatabaseProperties;
import com.jkinvest.jkl.base.security.annotation.LoginUser;
import com.jkinvest.jkl.base.security.model.SysUser;
import com.jkinvest.jkl.core.authority.service.common.LoginLogService;

import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 前端控制器
 * 首页
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
@Slf4j
@Validated
@RestController
@Api(value = "dashboard", tags = "首页")
public class DashboardController {

    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private DatabaseProperties databaseProperties;

    /**
     * 最近10天访问记录
     *
     * @return
     */
    @GetMapping("/dashboard/visit")
    public R<Map<String, Object>> index(@ApiIgnore @LoginUser SysUser user) {
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        data.put("totalVisitCount", loginLogService.findTotalVisitCount());
        data.put("todayVisitCount", loginLogService.findTodayVisitCount());
        data.put("todayIp", loginLogService.findTodayIp());

        data.put("lastTenVisitCount", loginLogService.findLastTenDaysVisitCount(null));
        data.put("lastTenUserVisitCount", loginLogService.findLastTenDaysVisitCount(user.getAccount()));

        data.put("browserCount", loginLogService.findByBrowser());
        data.put("operatingSystemCount", loginLogService.findByOperatingSystem());
        return R.success(data);
    }

    /**
     * 生成id
     *
     * @return
     */
    @GetMapping("/common/generateId")
    public R<Long> generate() {
        DatabaseProperties.Id id = databaseProperties.getId();
        return R.success(IdUtil.getSnowflake(id.getWorkerId(), id.getDataCenterId()).nextId());
    }
}
