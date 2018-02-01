package com.wuwii.module.sys.dao;

import com.wuwii.module.sys.entity.SysUserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/1/26 16:40</pre>
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
    @Resource
    private SysUserDao userDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testSave() {
        SysUserEntity user = new SysUserEntity();
        user.setUsername("wuwii");
        user.setPassword("123");
        userDao.save(user);
        Assert.assertEquals(user.getUsername(), userDao.query(user).get(0).getUsername());
    }
}
