package com.wuwii.util;

import com.wuwii.module.sys.entity.SysUserEntity;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/27 16:44</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("id", "1");
        Assert.assertEquals("1", stringRedisTemplate.opsForValue().get("id"));
    }

    /**
     * 测试存储对象，redis 需要对对象进行序列化，取出对象数据后比对，又要进行反序列化
     * 所以注册了 RedisTemplate ，专门处理这类情况
     */
    @Test
    public void test1() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(2L);
        sysUserEntity.setEmail("k@wuwii.com");
        ValueOperations<String, SysUserEntity> operations = redisTemplate.opsForValue();
        operations.set("user1", sysUserEntity);
        Assert.assertThat(sysUserEntity, Matchers.equalTo(operations.get("user1")));
    }

}
