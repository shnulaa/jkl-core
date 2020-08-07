package com.jkinvest.jkl.core.msgs.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jkinvest.jkl.base.base.mapper.SuperMapper;
import com.jkinvest.jkl.core.msgs.dto.MsgsCenterInfoPageResultDTO;
import com.jkinvest.jkl.core.msgs.dto.MsgsCenterInfoQueryDTO;
import com.jkinvest.jkl.core.msgs.entity.MsgsCenterInfo;

/**
 * <p>
 * Mapper 接口
 * 消息中心
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Repository
public interface MsgsCenterInfoMapper extends SuperMapper<MsgsCenterInfo> {
    /**
     * 查询消息中心分页数据
     *
     * @param page
     * @param param
     * @return
     */
    IPage<MsgsCenterInfoPageResultDTO> page(IPage page, @Param("data") MsgsCenterInfoQueryDTO param);
}
