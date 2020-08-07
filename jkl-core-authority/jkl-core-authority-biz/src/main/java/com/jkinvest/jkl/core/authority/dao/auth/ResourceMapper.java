package com.jkinvest.jkl.core.authority.dao.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.core.authority.dto.auth.ResourceQueryDTO;
import com.jkinvest.jkl.core.authority.entity.auth.Resource;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface ResourceMapper extends SuperMapper<Resource> {
    /**
     * 查询 拥有的资源
     *
     * @param resource
     * @return
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据唯一索引 保存或修改资源
     *
     * @param resource
     * @return
     */
    int saveOrUpdateUnique(Resource resource);

    List<Long> findMenuIdByResourceId(@Param("resourceIdList") List<Long> resourceIdList);
}
