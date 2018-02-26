package com.wuwii.module.sys.common.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ShiroUtils Tester.
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>02/26/2018</pre>
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ShiroUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getSession()
     */
    @Test
    public void testGetSession() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSubject()
     */
    @Test
    public void testGetSubject() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUserEntity()
     */
    @Test
    public void testGetUserEntity() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUserId()
     */
    @Test
    public void testGetUserId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setSessionAttribute(Object key, Object value)
     */
    @Test
    public void testGetSetSessionAttribute() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: isLogin()
     */
    @Test
    public void testIsLogin() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getKaptcha(String key)
     */
    @Test
    public void testGetKaptcha() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: generateSalt(int len)
     */
    @Test
    public void testGenerateSalt() throws Exception {
        System.out.println(ShiroUtils.generateSalt(20));

    }

    /**
     * Method: encryptPassword(String hashAlgorithm, String password, String salt)
     */
    @Test
    public void testEncryptPasswordForHashAlgorithmPasswordSalt() throws Exception {
        System.out.println(ShiroUtils.encryptPassword("SHA-256", "595165463", "64504144ef947affde28"));
    }

    /**
     * Method: encryptPassword(String hashAlgorithm, String password, String salt, int hashIterations)
     */
    @Test
    public void testEncryptPasswordForHashAlgorithmPasswordSaltHashIterations() throws Exception {
//TODO: Test goes here... 
    }


} 
