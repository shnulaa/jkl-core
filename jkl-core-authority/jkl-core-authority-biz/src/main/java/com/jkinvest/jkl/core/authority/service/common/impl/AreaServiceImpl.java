package com.jkinvest.jkl.core.authority.service.common.impl;

import static com.jkinvest.jkl.core.common.constant.CacheKey.AREA;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jkinvest.jkl.base.base.service.SuperCacheServiceImpl;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.core.authority.dao.common.AreaMapper;
import com.jkinvest.jkl.core.authority.entity.common.Area;
import com.jkinvest.jkl.core.authority.service.common.AreaService;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service

public class AreaServiceImpl extends SuperCacheServiceImpl<AreaMapper, Area> implements AreaService {

    @Override
    protected String getRegion() {
        return AREA;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recursively(List<Long> ids) {
        boolean removeFlag = removeByIds(ids);
        delete(ids);
        return removeFlag;
    }

    private void delete(List<Long> ids) {
        // 查询子节点
        List<Long> childIds = super.listObjs(Wraps.<Area>lbQ().select(Area::getId).in(Area::getParentId, ids), Convert::toLong);
        if (!childIds.isEmpty()) {
            removeByIds(childIds);
            delete(childIds);
        }
        log.debug("退出地区数据递归");
    }
}
