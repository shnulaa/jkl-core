package com.github.zuihou.area2;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jkinvest.jkl.core.authority.entity.common.Area;

import cn.hutool.log.StaticLog;


/**
 * 查询地区的经纬度
 *
 * @author zuihou
 * @date 2020年05月08日15:09:39
 */
@Component
public class LocationCityParserDecorator {

    public List<Area> parseProvinces(List<Area> list) {

        //有好方法的朋友可以提交PR
        StaticLog.info("查询出经纬度了. . . ");
        return list;
    }

}
