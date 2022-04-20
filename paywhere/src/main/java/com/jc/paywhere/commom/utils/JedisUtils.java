/**
 * FileName: JedisUtils
 * Author:   haichaoyang3
 * Date:     2019/6/14 14:17
 * Description: redis util tool
 * History:
 */
package com.jc.paywhere.commom.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Description:〈redis util tool〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
@Component
@Slf4j
public class JedisUtils {
   /* @Autowired
    private JedisPool jedisPool;


    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{}error", key, value, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public String set(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} expireTime:{}error", key, value, expireTime, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{}error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }


    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            log.error("del key:{}error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public Long expire(String key,int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key.getBytes(),expireTime);
        } catch (Exception e) {
            log.error("expire key:{}error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key.getBytes());
        } catch (Exception e) {
            log.error("ttl key:{}error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }


    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            log.error("exists key:{}error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }


    private void close(Jedis jedis) {
        if (null != jedis)
            jedis.close();
    }
    */

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Cacheable(value = "key", cacheManager = "cacheManager")
    public String set(String key, String value) {
//        System.out.println("excute set method");
        valueOperations.set(key, value);
        return key;
    }

    //    @CachePut(value = "key", cacheManager = "cacheManager")
    public void set(String key, String value, int expireTime) {
//        System.out.println("excute set method");
        valueOperations.set(key, value, expireTime, TimeUnit.SECONDS);
    }

    //    @Cacheable(value = "key")
    @CachePut(value = "key", key = "#key", cacheManager = "cacheManager")
    public Object get(String key) {
        System.out.println("excute get method");
        return valueOperations.get(key);
    }

    @CacheEvict(value = "key", key = "#key")
    public Boolean del(String key) {
        System.out.println("excute del method");
        return redisTemplate.delete(key);
    }

    public Boolean expire(String key, int expireTime) {
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public Long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


}