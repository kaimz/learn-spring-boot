package com.devframe.common.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * @author zhangkai
 * @ClassName: RedisUtil
 * @Description: redis工具类
 * @date 2017年9月26日 下午3:20:29
 */
public class RedisUtil implements Serializable {

    private static final long serialVersionUID = -1149678082569464779L;

    /**
     * 非切片额客户端连接
     */
    public static Jedis jedis;
    /**
     * Redis服务器IP
     */
    private static String addr;
    /**
     * Redis的端口号
     */
    private static int port;
    /**
     * 访问密码
     */
    private static String auth;
    /**
     * 可用连接实例的最大数目，默认值为8；
     * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
     */
    private static int maxActive;
    /**
     * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
     */
    private static int maxIdle;
    /**
     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
     */
    private static int maxWait;
    /**
     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
     */
    private static boolean testOnBorrow;
    /**
     * 非切片连接池
     */
    private static JedisPool jedisPool;

    // public static ShardedJedis shardedJedis;//切片额客户端连接

    // public static ShardedJedisPool shardedJedisPool;//切片连接池

    static {
        addr = "192.168.80.130";
        auth = "master";
        port = 6379;
        maxIdle = 8;
        maxActive = 8;
        maxWait = -1;
        initialPool();
    }

    public RedisUtil() {
        initialPool();
        // initialShardedPool();
        //shardedJedis = shardedJedisPool.getResource(); 
        jedis = getJedis();
    }

    /**
     * 初始化非切片池
     */
    private static void initialPool() {
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        jedisPool = new JedisPool(config, addr, port);
    }

    /*
     * 初始化切片池
     */ 
/*    private static  void initialShardedPool() 
    { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20); 
        config.setMaxIdle(5); 
        config.setMaxWaitMillis(1000l); 
        config.setTestOnBorrow(false); 
        // slave链接 
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        shards.add(new JedisShardInfo(addr, port, auth)); 

        // 构造池 
        shardedJedisPool = new ShardedJedisPool(config, shards); 
    }*/

    /**
     * 获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
                jedis.auth(auth);
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

   /* public  static void afterPropertiesSet() throws Exception {
        initialPool();
       // initialShardedPool();
        try {
              shardedJedis = shardedJedisPool.getResource(); 
        } catch (Exception e) {
            System.out.println("连接shardedJedisPool失败!");
        }
        try {
             jedis = jedisPool.getResource();
        } catch (Exception e) {
            System.out.println("连接jedisPool失败!");
        }
    }*/

    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getJedis();
    }

}
