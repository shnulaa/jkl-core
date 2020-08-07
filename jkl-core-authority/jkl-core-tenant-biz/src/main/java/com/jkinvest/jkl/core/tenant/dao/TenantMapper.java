package com.jkinvest.jkl.core.tenant.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.core.tenant.entity.Tenant;

/**
 * <p>
 * Mapper 接口
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Repository
@SqlParser(filter = true)
public interface TenantMapper extends SuperMapper<Tenant> {

    /**
     * 根据租户编码查询
     *
     * @param code 租户编码
     * @return
     */
    Tenant getByCode(@Param("code") String code);
}
