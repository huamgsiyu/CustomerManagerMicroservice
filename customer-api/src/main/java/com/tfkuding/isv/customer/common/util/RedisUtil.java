package com.tfkuding.isv.customer.common.util;

import com.tfkuding.isv.common.util.LogUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 12:35
 *
 * Redis缓存支持常用结构
 *  1、key-value
 *  2、key-map
 *  3、key-set
 *  4、key-list
 */
@Component
public class RedisUtil {
    private final static String TIP = "递增因子必须大于0";

    private static RedisTemplate<String, Object> redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 根据key指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return  设置是否成功
     */
    public static boolean expire(String key, long time){
        try {
            if(time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效，返回 -1代表在缓存中找不到key
     */
    public static long getExpire(String key){
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (expire == null) {
            return -1;
        }
        return expire;
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true存在，false不存在
     */
    public static boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key) != null;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值或多个
     */
    public static void delete(String ... key){
        if(key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else{
                List<String> keys = Arrays.stream(key).collect(Collectors.toList());
                redisTemplate.delete(keys);
            }
        }
    }

    /**
     * 缓存获取
     * @param key 键
     * @return 值
     */
    public static Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time){
        try {
            if(time > 0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }
    /**
     * 递增
     * @param key 键
     * @param increase 要增加几(大于0)
     * @return 如果递增成功，返回递增后的值，如果递增失败，返回-1
     */
    public static long increase(String key, long increase){
        if(increase < 0){
            throw new RuntimeException(TIP);
        }
        Long increment = redisTemplate.opsForValue().increment(key, increase);
        if (increment == null) {
            return -1;
        }
        return increment;
    }

    /**
     * 递减
     * @param key 键
     * @param decrease 要减少几(小于0)
     * @return 如果递减成功，返回递减后的值，如果递增失败，返回-1
     */
    public static long decrease(String key, long decrease){
        if(decrease < 0){
            throw new RuntimeException(TIP);
        }
        Long increment = redisTemplate.opsForValue().increment(key, -decrease);
        if (increment == null) {
            return -1;
        }
        return increment;
    }

    //================================Map=================================
    /**
     * 根据key，以及item获取map中指定值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object getMapItem(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object,Object> getMap(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 缓存map结构
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public static boolean setMap(String key, Map<String, Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 缓存map结构 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static boolean setMap(String key, Map<String,Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time > 0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 向指定key对应的map里的键设置value，如果没有该键，则创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean setMapItem(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 向指定key对应的map里的键设置value，如果没有该键，则创建；并设置过期时间
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean setMapItem(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 根据key找到Map，并根据item键删除value
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void deleteMapItem(String key, Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 根据key找到Map，并使用item判断是否有该键
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hasMapItem(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 根据key，找到Map，并指定递增item对应的value，如果不存在，就会创建一个，并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param increase 要增加几(大于0)
     * @return 递增后的值
     */
    public static double mapIncrease(String key, String item, double increase){
        if(increase < 0){
            throw new RuntimeException(TIP);
        }
        return redisTemplate.opsForHash().increment(key, item, increase);
    }

    /**
     * 根据key，找到Map，并指定递减item对应的value，如果不存在，就会创建一个，并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param decrease 要减少几(大于0)
     * @return 递减后的值
     */
    public static double mapDecrease(String key, String item, double decrease){
        if(decrease < 0){
            throw new RuntimeException(TIP);
        }
        return redisTemplate.opsForHash().increment(key, item, -decrease);
    }

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return 值
     */
    public static Set<Object> getSet(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            LogUtil.error(e);
            return null;
        }
    }

    /**
     * 根据key，找到Set，利用value从一个set中查询，判断其是否存在
     * @param key 键
     * @param item 值
     * @return true存在，false不存在
     */
    public static boolean hasSetItem(String key, Object item){
        try {
            return redisTemplate.opsForSet().isMember(key, item);
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param items 值 可以是多个
     * @return 成功个数
     */
    public static long setSet(String key, Object...items) {
        try {
            Long add = redisTemplate.opsForSet().add(key, items);
            if (add == null) {
                return 0;
            }
            return add;
        } catch (Exception e) {
            LogUtil.error(e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存，并设置缓存时间，如果时间小于等于0，设置为无限长
     * @param key 键
     * @param time 时间(秒)
     * @param items 值 可以是多个
     * @return 成功个数
     */
    public static long setSet(String key, long time, Object... items) {
        try {
            Long count = redisTemplate.opsForSet().add(key, items);
            if (count == null) {
                return 0;
            }
            if(time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            LogUtil.error(e);
            return 0;
        }
    }

    /**
     * 根据key，获取set缓存的长度
     * @param key 键
     * @return 返回Set缓存的长度，如果为0零表示key没有缓存
     */
    public static long getSetLength(String key){
        try {
            Long size = redisTemplate.opsForSet().size(key);
            if (size == null) {
                return 0;
            }
            return size;
        } catch (Exception e) {
            LogUtil.error(e);
            return 0;
        }
    }

    /**
     * 根据key找到Set，移除值为items的数据
     * @param key 键
     * @param items 值可以是多个
     * @return 成功移除的个数
     */
    public static long deleteSetItems(String key, Object ...items) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, items);
            if (count == null) {
                return 0;
            }
            return count;
        } catch (Exception e) {
            LogUtil.error(e);
            return 0;
        }
    }

    /**
     * 根据key获取list缓存的内容，从start开始到end结束
     * @param key 键
     * @param start 开始
     * @param end 结束  0到-1代表所有值
     * @return start ~ end的值
     */
    public static List<Object> getList(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            LogUtil.error(e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return 返回list的长度
     */
    public static long lGetListSize(String key){
        try {
            Long size = redisTemplate.opsForList().size(key);
            if (size == null)
                return 0;
            return size;
        } catch (Exception e) {
            LogUtil.error(e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引，index>=0时，0表头，1第二个元素，依次类推；index<0时，-1表尾-2倒数第二个元素，依次类推
     * @return list中的值
     */
    public static Object getListByIndex(String key, long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            LogUtil.error(e);
            return null;
        }
    }

    /**
     * 将item放到list中
     * @param key 键
     * @param value 值
     * @return  true缓存成功，false缓存失败
     */
    public static boolean setList(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 将item放到list中，并设置缓存时间
     * @param key 键
     * @param item 值
     * @param time 时间(秒)
     * @return  true缓存成功，false缓存失败
     */
    public static boolean setListItem(String key, Object item, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, item);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return  true缓存成功，false缓存失败
     */
    public static boolean setList(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return  true缓存成功，false缓存失败
     */
    public static boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param item 值
     * @return true更新成功，false更新失败
     */
    public static boolean updateListItem(String key, long index, Object item) {
        try {
            redisTemplate.opsForList().set(key, index, item);
            return true;
        } catch (Exception e) {
            LogUtil.error(e);
            return false;
        }
    }

    /**
     * 从list中移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param item 值
     * @return 成功移除的个数
     */
    public static long deleteListItem(String key, long count, Object item) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, item);
            if (remove == null)
                return 0;
            return remove;
        } catch (Exception e) {
            LogUtil.error(e);
            return 0;
        }
    }
}

