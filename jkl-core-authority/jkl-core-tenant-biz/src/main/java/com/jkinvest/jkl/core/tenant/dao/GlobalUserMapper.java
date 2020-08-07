package com.jkinvest.jkl.core.tenant.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.core.tenant.entity.GlobalUser;

/**
 * <p>
 * Mapper 接口
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Repository
@SqlParser(filter = true)
public interface GlobalUserMapper extends SuperMapper<GlobalUser> {

}
