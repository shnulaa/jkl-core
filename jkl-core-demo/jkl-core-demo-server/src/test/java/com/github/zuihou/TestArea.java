package com.github.zuihou;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.core.demo.dao.CCommonAreaMapper;
import com.jkinvest.jkl.core.demo.entity.CCommonArea;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/08/20
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestArea {
    @Autowired
    private CCommonAreaMapper mapper;

    @Test
    public void test() {

        Long id = 585823974982680865L;
        CCommonArea cCommonArea = mapper.selectById(id);
        System.out.println(cCommonArea);

        BaseContextHandler.setName("zuihou_authority_dev");
        cCommonArea = mapper.selectById(id);
        System.out.println(cCommonArea);

    }

}
