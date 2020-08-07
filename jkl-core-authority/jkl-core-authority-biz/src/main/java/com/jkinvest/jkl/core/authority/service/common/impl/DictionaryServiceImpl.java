package com.jkinvest.jkl.core.authority.service.common.impl;


import org.springframework.stereotype.Service;

import com.jkinvest.jkl.base.base.service.SuperServiceImpl;
import com.jkinvest.jkl.core.authority.dao.common.DictionaryMapper;
import com.jkinvest.jkl.core.authority.entity.common.Dictionary;
import com.jkinvest.jkl.core.authority.service.common.DictionaryService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 业务实现类
 * 字典类型
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service

public class DictionaryServiceImpl extends SuperServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

}
