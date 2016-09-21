package com.ligeng.common.redis;

import com.dream.utils.json.JsonObjectTools;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Component
public class RedisAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisAPI.class);

    private static final String ENCODE = "utf-8";
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 切换db
     *
     * @param index
     * @return
     */
    public boolean select(final int index) {
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.select(index);
                return true;
            }
        });
    }

    /**
     * set key and value to redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        return setForTimeout(key, value, 0);
    }

    /**
     * set key and value to redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setForTimeout(String key, String value, int expireTime) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
            return setForTimeout(key.getBytes(), value.getBytes(), expireTime);
        }
        return false;
    }

    public boolean setForTimeout(final byte[] key, final byte[] value, final int expireTime) {
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (expireTime > 0) {
                    connection.expire(key, expireTime);
                }
                return true;
            }
        });
    }

    /**
     * 存放递增元素
     * 如果该key不存在,它被设置为0执行操作之前
     *
     * @param key
     * @return
     */
    public Long incr(final String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.incr(key.getBytes());
                }
            });
        }
        return -1l;
    }


    public Long incrBy(final String key, final long val) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.incrBy(key.getBytes(), val);
                }
            });
        }
        return -1l;
    }

    /**
     * 设置失效时间
     *
     * @param key
     * @return
     */
    public boolean setTimeout(final String key, final int expireTime) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    if (expireTime > 0) {
                        connection.expire(key.getBytes(), expireTime);
                    }
                    return true;
                }
            });
        }
        return false;
    }

    /**
     * 判断某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean exist(final String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.exists(key.getBytes());
                }
            });
        }
        return false;
    }
    /**
     * 获取某个key的存活时长
     * -2 不存在key
     * -1 永久
     * >=0 时长
     * @param key
     * @return 存活 秒数
     */
    public Long ttl(final String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    Long ttl = connection.ttl(key.getBytes());
                    return ttl;
                }
            });
        }
        return -2L;
    }

    /**
     * hset 存放hash数据
     *
     * @param key
     * @return
     */
    public boolean hset(String key, String field, String val) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(field) && StringUtils.isNotBlank(val)) {
            return hset(key.getBytes(), field.getBytes(), val.getBytes());
        }
        return false;
    }

    public boolean hset(final byte[] key, final byte[] field, final byte[] val) {
        if (key != null && key.length > 0 && field != null && field.length > 0 && val != null && val.length > 0) {
            return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.hSet(key, field, val);
                }
            });
        }
        return false;
    }

    /**
     * 获取队列长度
     *
     * @param key
     * @return
     */
    public Long hlen(final String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.hLen(key.getBytes());
                }
            });
        }
        return -1L;
    }

    /**
     * map hset 到redis
     *
     * @param key key 对应的有数据 , 则不写入
     * @param map
     * @return
     */
    public boolean hsetMap(String key, Map<String, ?> map) {
        if (hlen(key) > 0) {
            return false;
        }
        if (map != null) {
            for (Map.Entry<String, ?> o : map.entrySet()) {
                hset(key, o.getKey(), JsonObjectTools.getStr(o));
            }
        }
        return true;
    }


    /**
     * List hset 到redis
     *
     * @param key  key 对应的有数据 , 则不写入
     * @param list
     * @return
     */
    public boolean hsetList(String key, List<? extends CommonKVRes> list) {
        if (hlen(key) > 0) {
            return false;
        }
        if (list != null) {
            for (CommonKVRes o : list) {
                hset(key, o.getId(), JsonObjectTools.getStr(o));
            }
        }
        return true;
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public String hget(final String key, final String field) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(field)) {
            return stringRedisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    return byte2Str(connection.hGet(key.getBytes(), field.getBytes()));
                }
            });
        }
        return null;
    }

    /**
     * 获取数据all
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(final String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Map<String, String>>() {
                public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
                    Map<String, String> v = null;
                    Map<byte[], byte[]> map = connection.hGetAll(key.getBytes());
                    if (map != null && map.size() > 0) {
                        Iterator<byte[]> iterator = map.keySet().iterator();
                        while (iterator.hasNext()) {
                            byte[] next = iterator.next();
                            byte[] vs = map.get(next);
                            if (v == null) {
                                v = new HashMap();
                            }
                            v.put(byte2Str(next), byte2Str(vs));
                        }
                    }
                    return v;
                }
            });
        }
        return null;
    }

    /**
     * 获取数据,同时更新 过期时间
     *
     * @param key
     * @return
     */
    public String get(final String key, final int exTime) {
        String value = null;
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    if (exTime > 0) {
                        connection.expire(key.getBytes(), exTime);
                    }
                    return byte2Str(connection.get(key.getBytes()));
                }
            });
        }
        return value;
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return get(key, 0);
    }

    /**
     * List rpush 到redis
     *
     * @param key  key 对应的有数据 , 则不写入
     * @param list
     * @return
     */
    public boolean rpushList(String key, List<?> list) {
        if (llen(key) > 0) {
            return false;
        }
        if (list != null && list.size() > 0) {
            for (Object o : list) {
                rpush(key, JsonObjectTools.getStr(o));
            }
        }
        return true;
    }

    /**
     * 从队列底部增加元素
     *
     * @param key
     * @return
     */
    public Long rpush(final String key, final String value) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
            return stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.rPush(key.getBytes(), value.getBytes());
                }
            });
        }
        return -1L;
    }

    /**
     * 从列表底部弹出元素
     *
     * @param key
     * @return
     */
    public String rpop(final String key) {
        String value = null;
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    return byte2Str(connection.rPop(key.getBytes()));
                }
            });
        }
        return value;
    }

    /**
     * 获取队列长度
     *
     * @param key
     * @return
     */
    public Long llen(final String key) {
        Long value = null;
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.lLen(key.getBytes());
                }
            });
        }
        return value;
    }

    public Long del(final String... keys) {
        if (keys != null && keys.length > 0) {
            return stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    for (String s : keys) {
                        connection.del(s.getBytes());
                    }
                    return 1L;
                }
            });
        }
        return -1L;
    }

    /**
     * 返回存储在key列表的特定元素。偏移量开始和停止是从0开始的索引
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(final String key, final int start, final int end) {
        List<String> value = null;
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.execute(new RedisCallback<List<String>>() {
                public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                    List<byte[]> bytes = connection.lRange(key.getBytes(), start, end);
                    List<String> list = new ArrayList();
                    if (bytes != null && bytes.size() > 0) {
                        for (byte[] aByte : bytes) {
                            String s = byte2Str(aByte);
                            if (StringUtils.isNotBlank(s)) {
                                list.add(s);
                            }
                        }
                    }
                    return list;
                }
            });
        }
        return value;
    }

    /**
     * 返回 key下的全列表元素
     *
     * @param key
     * @return
     */
    public List<String> lrange(String key) {
        return lrange(key, 0, -1);
    }

    private String byte2Str(byte[] bytes) {
        try {
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            return new String(bytes, ENCODE);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("redis-getdata-encode ex:", e);
        }
        return null;
    }

}
