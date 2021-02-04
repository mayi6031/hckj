package com.hckj.common.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * redis操作抽象类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/1 9:37
 */
public abstract class AbstractRedis implements RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(AbstractRedis.class);
    private String REDIS_PRE_KEY = "";

    public void init(String preKey) {
        this.REDIS_PRE_KEY = preKey;
    }

    private String getPreKey(String key) {
        return REDIS_PRE_KEY + key;
    }

    public abstract Jedis getJedis();

    public abstract void close(Jedis jedis);

    public String get(String key) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String value = jedis.get(key);
            return value;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    public void set(String key, String value) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    public void set(String key, String value, int seconds) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    public boolean setnx(String key, String value) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            long count = jedis.setnx(key, value);
            return count == 1;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return false;
    }

    public boolean setnx(String key, String value, int seconds) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            long count = jedis.setnx(key, value);
            if (count == 1) {
                jedis.expire(key, seconds);
            }
            return count == 1;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return false;
    }

    public long expire(String key, int seconds) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key, seconds);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return -1L;
    }

    public void del(String key) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    public Long incr(String key, int seconds) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            long count = jedis.incr(key);
            if (count == 1) {
                jedis.expire(key, seconds);
            }
            return count;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return 0L;
    }

    public void lset(String key, String... value) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
    }

    public List<String> lget(String key, long start, long end) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> lrange = jedis.lrange(key, start, end);
            return lrange;
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    public long lsize(String key) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.llen(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return 0L;
    }

    public Long rpush(String key, String value) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.rpush(key, value);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return 0L;
    }

    public String lpop(String key) {
        key = getPreKey(key);
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.lpop(key);
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }
}
