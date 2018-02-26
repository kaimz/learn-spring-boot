package com.wuwii.module.sys.service.impl;

import com.wuwii.module.sys.service.ShiroService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * ShiroServiceImpl Tester.
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>02/09/2018</pre>
 */
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ShiroServiceImplTest {
    @Resource
    private ShiroService shiroService;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getUserPermissions(long userId)
     */
    @Test
    public void testGetUserPermissions() throws Exception {
        System.out.println(shiroService.getUserPermissions(1));
    }

    /**
     * Method: getTokenObjectByToken(String token)
     */
    @Test
    public void testGetTokenObjectByToken() throws Exception {
        System.out.println(shiroService.getTokenObjectByToken("1231231"));
    }

    /**
     * Method: getUserById(long userId)
     */
    @Test
    public void testGetUserById() throws Exception {
        System.out.println(shiroService.getUserById(1));
    }


} 
