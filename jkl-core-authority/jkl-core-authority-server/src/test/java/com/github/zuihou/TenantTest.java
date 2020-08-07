package com.github.zuihou;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.base.database.mybatis.conditions.Wraps;
import com.jkinvest.jkl.base.database.mybatis.conditions.query.LbqWrapper;
import com.jkinvest.jkl.core.authority.dao.auth.RoleMapper;
import com.jkinvest.jkl.core.authority.dao.auth.UserMapper;
import com.jkinvest.jkl.core.authority.entity.auth.User;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/10/27
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TenantTest {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Before
    public void setTenant() {
        BaseContextHandler.setTenant("0000");
    }

    @Test
    public void test() {
        List<Long> userIdByCode = roleMapper.findUserIdByCode(new String[]{"SUPER_ADMIN"});
        System.out.println(userIdByCode.size());
    }

    @Test
    public void testFindUserByRoleId() {
        List<User> list = userMapper.findUserByRoleId(100L, "ad%min");
        log.info("list.size= " + list.size());
    }

    @Test
    public void testList() {
//        LbqWrapper<User> query = null;
        LbqWrapper<User> query = Wraps.lbQ();
        query.eq(User::getName, "超管");
        query.like(User::getAccount, "zuihou");
        query.orderByAsc(User::getCreateTime);
        userMapper.selectList(query);
    }


}
