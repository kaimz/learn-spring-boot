package com.wuwii.common.config;

import org.crazycake.shiro.RedisManager;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/2/28 8:34</pre>
 */
@ConfigurationProperties(prefix = "spring.redis")
public class CustomRedisManager extends RedisManager {

}
