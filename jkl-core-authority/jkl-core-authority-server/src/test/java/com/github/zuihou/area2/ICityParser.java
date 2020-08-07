package com.github.zuihou.area2;

import java.util.List;

import com.jkinvest.jkl.core.authority.entity.common.Area;


public interface ICityParser {

    /**
     * 解析得到省市区数据
     *
     * @param url 请求url
     * @return 城市
     */
    List<Area> parseProvinces(String url);
}
