package com.jkinvest.jkl.core.tenant.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.controller.SuperController;
import com.jkinvest.jkl.base.base.entity.SuperEntity;
import com.jkinvest.jkl.base.base.request.PageParams;
import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.database.mybatis.conditions.query.QueryWrap;
import com.jkinvest.jkl.base.log.annotation.SysLog;
import com.jkinvest.jkl.base.utils.BeanPlusUtil;
import com.jkinvest.jkl.base.utils.DateUtils;
import com.jkinvest.jkl.base.utils.StrHelper;
import com.jkinvest.jkl.core.authority.dto.auth.UserUpdatePasswordDTO;
import com.jkinvest.jkl.core.authority.entity.auth.User;
import com.jkinvest.jkl.core.authority.service.auth.UserService;
import com.jkinvest.jkl.core.common.constant.BizConstant;
import com.jkinvest.jkl.core.tenant.dto.GlobalUserPageDTO;
import com.jkinvest.jkl.core.tenant.dto.GlobalUserSaveDTO;
import com.jkinvest.jkl.core.tenant.dto.GlobalUserUpdateDTO;
import com.jkinvest.jkl.core.tenant.entity.GlobalUser;
import com.jkinvest.jkl.core.tenant.service.GlobalUserService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/globalUser")
@Api(value = "GlobalUser", tags = "全局账号")
@SysLog(enabled = false)
public class GlobalUserController extends SuperController<GlobalUserService, Long, GlobalUser, GlobalUserPageDTO, GlobalUserSaveDTO, GlobalUserUpdateDTO> {

    @Autowired
    private UserService userService;

    @Override
    public R<GlobalUser> handlerSave(GlobalUserSaveDTO model) {
        if (StrUtil.isEmpty(model.getTenantCode()) || BizConstant.SUPER_TENANT.equals(model.getTenantCode())) {
            return success(baseService.save(model));
        } else {
            BaseContextHandler.setTenant(model.getTenantCode());
            User user = BeanPlusUtil.toBean(model, User.class);
            user.setName(StrHelper.getOrDef(model.getName(), model.getAccount()));
            if (StrUtil.isEmpty(user.getPassword())) {
                user.setPassword(BizConstant.DEF_PASSWORD);
            }
            user.setStatus(true);
            userService.initUser(user);
            return success(BeanPlusUtil.toBean(user, GlobalUser.class));
        }
    }

    @Override
    public R<GlobalUser> handlerUpdate(GlobalUserUpdateDTO model) {
        if (StrUtil.isEmpty(model.getTenantCode()) || BizConstant.SUPER_TENANT.equals(model.getTenantCode())) {
            return success(baseService.update(model));
        } else {
            BaseContextHandler.setTenant(model.getTenantCode());
            User user = BeanPlusUtil.toBean(model, User.class);
            userService.updateUser(user);
            return success(BeanPlusUtil.toBean(user, GlobalUser.class));
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账号", dataType = "string", paramType = "query"),
    })
    @ApiOperation(value = "检测账号是否可用", notes = "检测账号是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String tenantCode, @RequestParam String account) {
        if (StrUtil.isEmpty(tenantCode) || BizConstant.SUPER_TENANT.equals(tenantCode)) {
            return success(baseService.check(account));
        } else {
            BaseContextHandler.setTenant(tenantCode);
            return success(userService.check(account));
        }
    }

    private void handlerUserWrapper(QueryWrap<User> wrapper, PageParams<GlobalUserPageDTO> params) {
        if (CollUtil.isNotEmpty(params.getMap())) {
            Map<String, String> map = params.getMap();
            //拼装区间
            for (Map.Entry<String, String> field : map.entrySet()) {
                String key = field.getKey();
                String value = field.getValue();
                if (StrUtil.isEmpty(value)) {
                    continue;
                }
                if (key.endsWith("_st")) {
                    String beanField = StrUtil.subBefore(key, "_st", true);
                    wrapper.ge(getDbField(beanField, getEntityClass()), DateUtils.getStartTime(value));
                }
                if (key.endsWith("_ed")) {
                    String beanField = StrUtil.subBefore(key, "_ed", true);
                    wrapper.le(getDbField(beanField, getEntityClass()), DateUtils.getEndTime(value));
                }
            }
        }
    }


    @Override
    public void query(PageParams<GlobalUserPageDTO> params, IPage<GlobalUser> page, Long defSize) {
        GlobalUserPageDTO model = params.getModel();
        if (StrUtil.isEmpty(model.getTenantCode()) || BizConstant.SUPER_TENANT.equals(model.getTenantCode())) {
            QueryWrap<GlobalUser> wrapper = handlerWrapper(null, params);
            wrapper.lambda().eq(GlobalUser::getTenantCode, model.getTenantCode())
                    .like(GlobalUser::getAccount, model.getAccount())
                    .like(GlobalUser::getName, model.getName());
            baseService.page(page, wrapper);
            return;
        }
        BaseContextHandler.setTenant(model.getTenantCode());

        IPage<User> userPage = params.buildPage();
        QueryWrap<User> wrapper = Wraps.q();
        handlerUserWrapper(wrapper, params);
        wrapper.lambda()
                .like(User::getAccount, model.getAccount())
                .like(User::getName, model.getName());

        userService.page(userPage, wrapper);

        page.setCurrent(userPage.getCurrent());
        page.setSize(userPage.getSize());
        page.setTotal(userPage.getTotal());
        page.setPages(userPage.getPages());
        List<GlobalUser> list = BeanPlusUtil.toBeanList(userPage.getRecords(), GlobalUser.class);
        page.setRecords(list);
    }


    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "ids[]", value = "主键id", dataType = "array", paramType = "query"),
    })
    public R<Boolean> delete(@RequestParam String tenantCode, @RequestParam("ids[]") List<Long> ids) {
        if (StrUtil.isEmpty(tenantCode) || BizConstant.SUPER_TENANT.equals(tenantCode)) {
            return success(baseService.removeByIds(ids));
        } else {
            BaseContextHandler.setTenant(tenantCode);
            return success(userService.remove(ids));
        }
    }


    /**
     * 修改密码
     *
     * @param model 修改实体
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/password")
    public R<Boolean> updatePassword(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO model) {
        if (StrUtil.isEmpty(model.getTenantCode()) || BizConstant.SUPER_TENANT.equals(model.getTenantCode())) {
            return success(baseService.updatePassword(model));
        } else {
            BaseContextHandler.setTenant(model.getTenantCode());
            return success(userService.updatePassword(model));
        }
    }
}
