package com.jkinvest.jkl.core.authority.service.common;

import com.jkinvest.jkl.base.base.service.SuperService;
import com.jkinvest.jkl.core.authority.entity.common.Parameter;

/**
 * <p>
 * 业务接口
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @date 2020-02-05
 */
public interface ParameterService extends SuperService<Parameter> {
    /**
     * 根据参数键查询参数值
     *
     * @param key    参数键
     * @param defVal 参数值
     * @return
     */
    String getValue(String key, String defVal);
}
