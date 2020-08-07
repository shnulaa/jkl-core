package com.jkinvest.jkl.core.authority.service.common;

import java.util.List;

import com.jkinvest.jkl.base.base.service.SuperCacheService;
import com.jkinvest.jkl.core.authority.entity.common.Area;

/**
 * <p>
 * 业务接口
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
public interface AreaService extends SuperCacheService<Area> {

    /**
     * 递归删除
     *
     * @param ids
     * @return
     */
    boolean recursively(List<Long> ids);
}
