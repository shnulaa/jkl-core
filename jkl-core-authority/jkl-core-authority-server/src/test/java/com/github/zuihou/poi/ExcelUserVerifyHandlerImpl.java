package com.github.zuihou.poi;

import com.jkinvest.jkl.core.authority.dto.auth.UserExcelVO;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.hutool.core.util.StrUtil;

public class ExcelUserVerifyHandlerImpl implements IExcelVerifyHandler<UserExcelVO> {

    @Override
    public ExcelVerifyHandlerResult verifyHandler(UserExcelVO obj) {
        StringBuilder builder = new StringBuilder();
        boolean bool = true;
        if (StrUtil.isEmpty(obj.getAccount())) {
            builder.append("名称不能为空");
            bool = false;
        } else if (obj.getName().length() > 10) {
            builder.append("名称长度不能超过10");
            bool = false;
        }
        return new ExcelVerifyHandlerResult(bool, builder.toString());
    }

}
