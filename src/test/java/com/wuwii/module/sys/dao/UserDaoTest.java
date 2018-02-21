package com.wuwii.module.sys.dao;

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
    public void test() {
        /*SysUserEntity user = new SysUserEntity();
        user.setUsername("124");
        user.setPassword("123");
        userDao.save(user);*/
        //Assert.assertThat("123".equals(us));
        System.out.println(userDao.queryList(null));
    }
}
