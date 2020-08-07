package com.jkinvest.jkl.core.authority.service.common.impl;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.base.log.entity.OptLogDTO;
import com.jkinvest.jkl.base.utils.BeanPlusUtil;
import com.jkinvest.jkl.core.authority.dao.common.OptLogMapper;
import com.jkinvest.jkl.core.authority.entity.common.OptLog;
import com.jkinvest.jkl.core.authority.service.common.OptLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service

public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {

    @Override
    public boolean save(OptLogDTO entity) {
        return super.save(BeanPlusUtil.toBean(entity, OptLog.class));
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum);
    }
}
