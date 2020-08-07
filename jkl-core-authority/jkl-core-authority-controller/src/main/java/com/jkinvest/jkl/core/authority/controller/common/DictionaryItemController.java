package com.jkinvest.jkl.core.authority.controller.common;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkinvest.jkl.base.base.controller.SuperCacheController;
import com.jkinvest.jkl.base.base.request.PageParams;
import com.jkinvest.jkl.base.database.mybatis.conditions.query.QueryWrap;
import com.jkinvest.jkl.base.security.annotation.PreAuth;
import com.jkinvest.jkl.core.authority.dto.common.DictionaryItemSaveDTO;
import com.jkinvest.jkl.core.authority.dto.common.DictionaryItemUpdateDTO;
import com.jkinvest.jkl.core.authority.entity.common.DictionaryItem;
import com.jkinvest.jkl.core.authority.service.common.DictionaryItemService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionaryItem")
@Api(value = "DictionaryItem", tags = "字典项")
@PreAuth(replace = "dict:")
public class DictionaryItemController extends SuperCacheController<DictionaryItemService, Long, DictionaryItem, DictionaryItem, DictionaryItemSaveDTO, DictionaryItemUpdateDTO> {
    @Override
    public QueryWrap<DictionaryItem> handlerWrapper(DictionaryItem model, PageParams<DictionaryItem> params) {
        QueryWrap<DictionaryItem> wrapper = super.handlerWrapper(model, params);
        wrapper.lambda().ignore(DictionaryItem::setDictionaryType)
                .eq(DictionaryItem::getDictionaryType, model.getDictionaryType());
        return wrapper;
    }

}
