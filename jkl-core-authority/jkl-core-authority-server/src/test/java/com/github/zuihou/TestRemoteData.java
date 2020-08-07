package com.github.zuihou;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.database.mybatis.conditions.update.LbuWrapper;
import com.jkinvest.jkl.base.dozer.DozerUtils;
import com.jkinvest.jkl.base.injection.core.InjectionCore;
import com.jkinvest.jkl.base.model.RemoteData;
import com.jkinvest.jkl.core.authority.dao.common.AreaMapper;
import com.jkinvest.jkl.core.authority.entity.common.Area;
import com.jkinvest.jkl.core.authority.service.common.impl.AreaServiceImpl;

import lombok.extern.slf4j.Slf4j;

;

/**
 * 测试类
 *
 * @author zuihou
 * @date 2019/06/26
 */


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestRemoteData {

    @Autowired
    private DozerUtils dozer;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private InjectionCore injectionComponent;
    @Autowired
    private AreaServiceImpl areaService;

    @Before
    public void setTenant() {
        BaseContextHandler.setTenant("0000");
    }

    @Test
    public void testAreaGet() {
        Area area = areaMapper.selectById(1L);
        System.out.println(area);
    }

    @Test
    public void testAreaList() {
        List<Area> areas = areaMapper.selectList(null);
        System.out.println(areas);
    }

    @Test
    public void testAreaPage() {
        IPage<Area> page = new Page(1, 20);
        areaMapper.selectPage(page, null);
        System.out.println(page.getRecords());
    }

    @Test
    public void testAreaSave() {
        Area area = Area.builder()
                .code("aaaa")
                .label("vvvvv")
                .build();
        area.setLevel(new RemoteData<>("AREA_LEVEL"));

        int insert = areaMapper.insert(area);
        System.out.println(area);
    }

    @Test
    public void testAreaUpdate() {
        Area area = Area.builder()
                .id(668136559643459617L)
                .code("bb")
                .label("ddd")
                .build();
        area.setLevel(new RemoteData<>("AREA_LEVEL"));

        int insert = areaMapper.updateById(area);
        System.out.println(area);
    }

    @Test
    public void testAreaUpdateWrp() {
        LbuWrapper<Area> up = Wraps.lbU();
        up.set(Area::getLevel, new RemoteData<>(""));
        up.eq(Area::getId, 668136559643459617L);
        int insert = areaMapper.update(null, up);
        System.out.println(insert);
    }

    @Test
    public void getOne() throws Exception {
        Area area = Area.builder()
                .id(668136559643459617L)
                .code("bb")
                .label("ddd")
                .build();
        area.setLevel(new RemoteData<>("COLLEGE"));

        injectionComponent.injection(area);

        Area area2 = Area.builder()
                .id(668136559643459617L)
                .code("bb")
                .label("ddd")
                .build();
        area2.setLevel(new RemoteData<>("COLLEGE"));

        injectionComponent.injection(area2);

        log.info("area={}", area);
    }

}
