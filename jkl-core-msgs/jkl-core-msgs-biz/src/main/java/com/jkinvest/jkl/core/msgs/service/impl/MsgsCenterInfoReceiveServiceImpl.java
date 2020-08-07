package com.jkinvest.jkl.core.msgs.service.impl;


import org.springframework.stereotype.Service;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.core.msgs.dao.MsgsCenterInfoReceiveMapper;
import com.jkinvest.jkl.core.msgs.entity.MsgsCenterInfoReceive;
import com.jkinvest.jkl.core.msgs.service.MsgsCenterInfoReceiveService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 消息中心 接收表
 * 全量数据
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service

public class MsgsCenterInfoReceiveServiceImpl extends SuperServiceImpl<MsgsCenterInfoReceiveMapper, MsgsCenterInfoReceive> implements MsgsCenterInfoReceiveService {

}
