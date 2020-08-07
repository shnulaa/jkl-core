package com.jkinvest.jkl.core.authority.service.common;

import java.time.LocalDateTime;

import com.jkinvest.jkl.base.base.service.SuperService;
import com.jkinvest.jkl.base.log.entity.OptLogDTO;
import com.jkinvest.jkl.core.authority.entity.common.OptLog;

/**
 * <p>
 * 业务接口
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
public interface OptLogService extends SuperService<OptLog> {

    /**
     * 保存日志
     *
     * @param entity
     * @return
     */
    boolean save(OptLogDTO entity);

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
