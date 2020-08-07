package com.jkinvest.jkl.core.authority.controller.auth;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.controller.SuperCacheController;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.base.utils.BeanPlusUtil;
import com.jkinvest.jkl.core.authority.dto.auth.ResourceSaveDTO;
import com.jkinvest.jkl.core.authority.dto.auth.ResourceUpdateDTO;
import com.jkinvest.jkl.core.authority.entity.auth.Resource;
import com.jkinvest.jkl.core.authority.service.auth.ResourceService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resource")
@Api(value = "Resource", tags = "资源")
@PreAuth(replace = "resource:")
public class ResourceController extends SuperCacheController<ResourceService, Long, Resource, Resource, ResourceSaveDTO, ResourceUpdateDTO> {
    @Override
    public R<Resource> handlerSave(ResourceSaveDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        baseService.saveWithCache(resource);
        return success(resource);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return success(baseService.removeByIdWithCache(ids));
    }

    @Override
    public R<Resource> handlerUpdate(ResourceUpdateDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        baseService.updateById(resource);
        return success(resource);
    }


//    /**
//     * 查询用户可用的所有资源
//     *
//     * @param resource <br>
//     *                 appCode 应用code * <br>
//     *                 type 类型 <br>
//     *                 group 分组 <br>
//     *                 resourceId 上级资源id <br>
//     *                 accountId 当前登录人id
//     * @return
//     */
//    @ApiOperation(value = "查询用户可用的所有资源", notes = "查询用户可用的所有资源")
//    @GetMapping("/visible")
//    @SysLog("查询用户可用的所有资源")
//    public R<List<Resource>> visible(ResourceQueryDTO resource) {
//        if (resource == null) {
//            resource = new ResourceQueryDTO();
//        }
//
//        if (resource.getUserId() == null) {
//            resource.setUserId(getUserId());
//        }
//        return success(baseService.findVisibleResource(resource));
//    }


}
